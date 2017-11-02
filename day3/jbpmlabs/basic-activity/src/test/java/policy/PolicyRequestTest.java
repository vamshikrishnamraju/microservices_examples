package policy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;

import junit.framework.Assert;
import model.Driver;
import model.Policy;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.test.JBPMHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;

public class PolicyRequestTest {

	private KieSession session;
	private RuntimeManager manager;
	private MyEventListener listener;

	@BeforeClass
	public static void setUpEngine() {

		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();

	}

	@Before
	public void setUpSession() {

		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory
				.get()
				.newClasspathKmoduleDefaultBuilder("policyBase", "insurance")
				.entityManagerFactory(
						Persistence
								.createEntityManagerFactory("org.jbpm.persistence.jpa"))
				.get();
		manager = RuntimeManagerFactory.Factory.get()
                          .newPerRequestRuntimeManager(environment);
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();
		session.getWorkItemManager().registerWorkItemHandler("Service Task",
				new ServiceTaskHandler());
		listener = new MyEventListener();
		session.addEventListener(listener);
	}

	@After
	public void endSession() {
		if (manager != null) {
			manager.disposeRuntimeEngine(manager.getRuntimeEngine(EmptyContext
					.get()));
			manager.close();
		}
	}

	@Test
	public void testSimpleRequest() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1998, 0, 0, driver);
		params.put("policy", policy);
		ProcessInstance pi = session.startProcess("policy.Request", params);
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_COMPLETED);
		Policy updatedPolicy = (Policy) listener.getVariable("policy");
		Assert.assertEquals((Integer) 745, updatedPolicy.getPrice());
		Assert.assertEquals((Integer) 5, updatedPolicy.getPriceDiscount());
		Assert.assertNull(updatedPolicy.getRejection());
	}

	@Test
	public void testDriverMissingRequest() {
		Map<String, Object> params = new HashMap<String, Object>();
		Policy policy = new Policy(new Date(), "Auto", 1998, 0, 0, null);
		params.put("policy", policy);
		session.startProcess("policy.Request", params);
		session.fireAllRules();
		Policy updatedPolicy = (Policy) listener.getVariable("policy");
		Assert.assertEquals((Integer) 0, updatedPolicy.getPrice());
		Assert.assertEquals((Integer) 0, updatedPolicy.getPriceDiscount());
		Assert.assertNotNull(updatedPolicy.getRejection());
		Assert.assertEquals("No driver information given", updatedPolicy
				.getRejection().getReason());
	}

	@Test
	public void testCarTooOld() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1948, 0, 0, driver);
		params.put("policy", policy);
		session.startProcess("policy.Request", params);
		session.fireAllRules();
		Policy updatedPolicy = (Policy) listener.getVariable("policy");
		Assert.assertEquals((Integer)745, updatedPolicy.getPrice());
		Assert.assertEquals((Integer) 5, updatedPolicy.getPriceDiscount());
		Assert.assertNotNull(updatedPolicy.getRejection());
		Assert.assertEquals("We do not insure vehicles made before 1950", updatedPolicy
				.getRejection().getReason());

	}

	@Test
	public void testDriverTooYoung() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 12, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1999, 0, 0, driver);
		params.put("policy", policy);
		session.startProcess("policy.Request", params);
		Policy updatedPolicy = (Policy) listener.getVariable("policy");
		Assert.assertEquals((Integer)900, updatedPolicy.getPrice());
		Assert.assertEquals((Integer) 5, updatedPolicy.getPriceDiscount());
		Assert.assertNotNull(updatedPolicy.getRejection());
		Assert.assertEquals("We do not insure drivers < 15 years old", updatedPolicy
				.getRejection().getReason());

	}

	private class MyEventListener implements ProcessEventListener {

		private Map<String, Object> variables = new HashMap<String, Object>(3);

		@Override
		public void beforeProcessStarted(ProcessStartedEvent event) {
		}

		@Override
		public void afterProcessStarted(ProcessStartedEvent event) {
		}

		@Override
		public void beforeProcessCompleted(ProcessCompletedEvent event) {
		}

		@Override
		public void afterProcessCompleted(ProcessCompletedEvent event) {
		}

		@Override
		public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		}

		@Override
		public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		}

		@Override
		public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		}

		@Override
		public void afterNodeLeft(ProcessNodeLeftEvent event) {
		}

		@Override
		public void beforeVariableChanged(ProcessVariableChangedEvent event) {
		}

		@Override
		public void afterVariableChanged(ProcessVariableChangedEvent event) {
			variables.put(event.getVariableId(), event.getNewValue());

		}

		public Object getVariable(String name) {
			return variables.get(name);
		}

	}

}
