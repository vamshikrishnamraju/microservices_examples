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
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

public class PolicyRequestTest {

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
		properties.setProperty("joe", "");
		properties.setProperty("mary", "service");
		properties.setProperty("john", "service");
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
	public void testSimpleRequest() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1998, 0, 0, driver);
		params.put("policy", policy);
		ProcessInstance pi = session.startProcess("policy.Request", params);
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_COMPLETED);
	}

	@Test
	public void testDriverMissingRequest() {
		Map<String, Object> params = new HashMap<String, Object>();
		Policy policy = new Policy(new Date(), "Auto", 1998, 0, 0, null);
		params.put("policy", policy);
		ProcessInstance pi = session.startProcess("policy.Request", params);
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ABORTED);

	}

	@Test
	public void testCarTooOld() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1948, 0, 0, driver);
		params.put("policy", policy);
		ProcessInstance pi = session.startProcess("policy.Request", params);
		session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner(
				"john", "en-UK").get(0);
		Assert.assertNotNull(task1);
		taskService.claim(task1.getId(), "john");
		taskService.start(task1.getId(), "john");
		// correct invalid auto year
		policy.setVehicleYear(1958);
		policy.setRejection(null);
		taskService.complete(task1.getId(), "john", params);

	}

	@Test
	public void testDriverTooYoung() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 12, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 1999, 0, 0, driver);
		params.put("policy", policy);
		ProcessInstance pi = session.startProcess("policy.Request", params);
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);
		
		TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner(
				"mary", "en-UK").get(0);
		Assert.assertNotNull(task1);

		taskService.claim(task1.getId(), "mary");
		taskService.start(task1.getId(), "mary");
		// allow rejection to stand - invalid application
		taskService.complete(task1.getId(), "mary", params);
		
	}

}
