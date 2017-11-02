package policy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Persistence;

import junit.framework.Assert;
import model.Driver;
import model.Policy;
import model.Rejection;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.jbpm.test.JBPMHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.event.KnowledgeRuntimeEventManager;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

import bpm.RecordingProcessEventListener;

public class PolicyRequestTest {

	private KieSession session;
	private TaskService taskService;
	private RuntimeManager manager;
	private KnowledgeRuntimeLogger logger;
	private RecordingProcessEventListener listener;

	@BeforeClass
	public static void setUpEngine() {

		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();

	}

	@Before
	public void setUpSession() {
		Properties properties = new Properties();
		properties.setProperty("joe", "");
		properties.setProperty("mary", "actuary");
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
		logger = KnowledgeRuntimeLoggerFactory
				.newConsoleLogger((KnowledgeRuntimeEventManager) session);
		listener = new RecordingProcessEventListener();
		session.addEventListener(listener);
	}

	@After
	public void endSession() {
		if (logger != null)
			logger.close();

		if (manager != null) {
			manager.disposeRuntimeEngine(manager.getRuntimeEngine(EmptyContext
					.get()));
			manager.close();
		}
	}

	@Test
	public void testLowRisk() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 2010, 0, 0, driver);
		params.put("request", policy);
		ProcessInstance pi = session.startProcess("policy.Risk", params);
		// session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner(
				"mary", "en-UK").get(0);

		Assert.assertNotNull(task1);
		taskService.claim(task1.getId(), "mary");
		taskService.start(task1.getId(), "mary");
		Map<String, Object> taskParams = new HashMap<String, Object>();
		taskParams.put("risk", (Integer) 400);
		taskService.complete(task1.getId(), "mary", taskParams);
		System.out.println(listener.graphPath());
	}

	@Test
	public void testHighRisk() {
		Map<String, Object> params = new HashMap<String, Object>();
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 2010, 0, 0, driver);
		params.put("request", policy);
		ProcessInstance pi = session.startProcess("policy.Risk", params);
		// session.fireAllRules();
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_ACTIVE);

		TaskSummary task1 = taskService.getTasksAssignedAsPotentialOwner(
				"mary", "en-UK").get(0);

		Assert.assertNotNull(task1);
		taskService.claim(task1.getId(), "mary");
		taskService.start(task1.getId(), "mary");
		Map<String, Object> taskParams = new HashMap<String, Object>();
		taskParams.put("risk", (Integer) 600);
		taskService.complete(task1.getId(), "mary", taskParams);

		Assert.assertEquals(ProcessInstance.STATE_ABORTED, listener.getStatus());
		Assert.assertEquals("90277", listener.getErrorCode());
		Rejection rejection = (Rejection) session
				.getObjects(new ClassObjectFilter(Rejection.class)).iterator()
				.next();
		Assert.assertNotNull(rejection);
		Assert.assertEquals("Risk > 500", rejection.getReason());
		Integer risk = listener.getVariable("risk", Integer.class);
		Assert.assertNotNull(risk);
		Assert.assertEquals((Integer) 600, risk);
		System.out.println(listener.graphPath());

	}
}
