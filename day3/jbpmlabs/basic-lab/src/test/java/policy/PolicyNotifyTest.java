package policy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Persistence;

import junit.framework.Assert;
import model.Driver;
import model.Policy;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
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
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

public class PolicyNotifyTest {

	private KieSession session;
	private TaskService taskService;
	private RuntimeManager manager;

	@BeforeClass
	public static void setUpEngine() {

		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();

	}

	@Before
	public void setUpSession() {
		Properties properties = new Properties();
		properties.setProperty("joe", "customer");
		UserGroupCallback userGroupCallback = new JBossUserGroupCallbackImpl(
				properties);
		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory
				.get()
				.newClasspathKmoduleDefaultBuilder("policyBase", "insurance")
				.entityManagerFactory(
						Persistence
								.createEntityManagerFactory("org.jbpm.persistence.jpa"))
				.userGroupCallback(userGroupCallback).get();
		manager = RuntimeManagerFactory.Factory.get()
                          .newPerRequestRuntimeManager(environment);
		taskService = manager.getRuntimeEngine(EmptyContext.get())
				.getTaskService();
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();
		session.getWorkItemManager().registerWorkItemHandler("Service Task",
				new ServiceTaskHandler());
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
	public void testApproved() {

		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		ProcessInstance pi = session.startProcess("policy.Notify");
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		long taskId = taskService
				.getTasksAssignedAsPotentialOwner("joe", "en-UK").get(0)
				.getId();
		taskService.claim(taskId, "joe");
		taskService.start(taskId, "joe");
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1948, 0, 0, driver);
		params.put("policy", policy);
		taskService.complete(taskId, "joe", params);
		assertProcessEnded(pi.getId());
		String letter = (String) pel.getVariable("letter");
		Assert.assertNotNull(letter);
		Assert.assertTrue("is approval letter", letter.indexOf("approved") > 0);
		Assert.assertEquals((Integer) 650, ((Policy) pel.getVariable("policy")).getPrice());

	}

	@Test
	public void testDenied() {

		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		ProcessInstance pi = session.startProcess("policy.Notify");
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		long taskId = taskService
				.getTasksAssignedAsPotentialOwner("joe", "en-UK").get(0)
				.getId();
		taskService.claim(taskId, "joe");
		taskService.start(taskId, "joe");
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 1,
				3, 720);
		Policy policy = new Policy(new Date(), "Auto", 1948, 0, 0, driver);
		params.put("policy", policy);
		taskService.complete(taskId, "joe", params);
		assertProcessEnded(pi.getId());
		String letter = (String) pel.getVariable("letter");
		Assert.assertNotNull(letter);
		Assert.assertTrue("is rejection letter", letter.indexOf("denied") > 0);

	}
	
	@Test
	public void testYouthPrice() {

		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		ProcessInstance pi = session.startProcess("policy.Notify");
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		long taskId = taskService
				.getTasksAssignedAsPotentialOwner("joe", "en-UK").get(0)
				.getId();
		taskService.claim(taskId, "joe");
		taskService.start(taskId, "joe");
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("Johnny Doe", 16, "555124444", "L134990", 0,
				0, 500);
		Policy policy = new Policy(new Date(), "Auto", 1985, 0, 0, driver);
		params.put("policy", policy);
		taskService.complete(taskId, "joe", params);
		assertProcessEnded(pi.getId());
		String letter = (String) pel.getVariable("letter");
		Assert.assertNotNull(letter);
		Assert.assertTrue("is rejection letter", letter.indexOf("approved") > 0);
		Assert.assertEquals((Integer) 895, ((Policy) pel.getVariable("policy")).getPrice());

	}
	
	@Test
	public void testRiskPrice() {

		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		ProcessInstance pi = session.startProcess("policy.Notify");
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		long taskId = taskService
				.getTasksAssignedAsPotentialOwner("joe", "en-UK").get(0)
				.getId();
		taskService.claim(taskId, "joe");
		taskService.start(taskId, "joe");
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("Larry Smith", 35, "555132222", "L135991", 1,
				2, 680);
		Policy policy = new Policy(new Date(), "Auto", 1985, 0, 0, driver);
		params.put("policy", policy);
		taskService.complete(taskId, "joe", params);
		assertProcessEnded(pi.getId());
		String letter = (String) pel.getVariable("letter");
		Assert.assertNotNull(letter);
		Assert.assertTrue("is rejection letter", letter.indexOf("approved") > 0);
		Assert.assertEquals((Integer) 1050, ((Policy) pel.getVariable("policy")).getPrice());

	}

	private boolean assertProcessEnded(long id) {
		return session.getProcessInstance(id) == null;
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
