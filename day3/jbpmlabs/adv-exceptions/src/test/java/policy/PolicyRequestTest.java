package policy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;
import model.Driver;
import model.Policy;
import model.Rejection;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.bpmn2.handler.SignallingTaskHandlerDecorator;
import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

import service.ExceptionHandler;
import bpm.RecordingProcessEventListener;

public class PolicyRequestTest {

	private KieSession session;
	private RuntimeManager manager;

	@Before
	public void setUpSession() {
		// Create simple user group callback
		Properties properties = new Properties();
		properties.setProperty("joe", "");
		properties.setProperty("mary", "actuary");
		UserGroupCallback userGroupCallback = new JBossUserGroupCallbackImpl(
				properties);
		// Create KieBase for "policyBase"
		KieBase base = KieServices.Factory.get().newKieClasspathContainer()
				.getKieBase("policyBase");
		// Create singleton RuntimeManager
		RuntimeEnvironment environment = 
				RuntimeEnvironmentBuilder.Factory.get()
				.newEmptyBuilder()
				.knowledgeBase(base)
				.persistence(false)
				.userGroupCallback(userGroupCallback)
				.get();
		manager = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(environment);
		// Get KieSession
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();
		// Add process event listener
		session.addEventListener(new RecordingProcessEventListener());
		
		String eventType = "Error-90277";
        SignallingTaskHandlerDecorator signallingTaskWrapper = new SignallingTaskHandlerDecorator(ServiceTaskHandler.class, eventType);
        signallingTaskWrapper.setWorkItemExceptionParameterName(ExceptionHandler.EXCEPTION_PARAM);
        session.getWorkItemManager().registerWorkItemHandler("Service Task", signallingTaskWrapper);
	}

	@After
	public void endSession() {

		if (manager != null) {
			// dispose of runtime engine and manager
			manager.disposeRuntimeEngine(manager.getRuntimeEngine(EmptyContext
					.get()));
			manager.close();
		}
	}

	@Test
	public void testLowRisk() {
		
		// Given a risk of 100 make sure the process completes normally
		Policy policy = getTestRequest();
		policy.setRisk(100);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("request", policy);

		ProcessInstance pi = session.startProcess("policy.Risk", params);
		session.fireAllRules();
		
		Assert.assertEquals(ProcessInstance.STATE_COMPLETED, pi.getState());
	}

	@Test
	public void testHighRisk() {
		
		// Given a risk of 600 make sure the process aborts with error 90277
		Policy policy = getTestRequest();
		policy.setRisk(600);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("request", policy);
		
		ProcessInstance pi = session.startProcess("policy.Risk", params);
		session.fireAllRules();

		Assert.assertEquals(ProcessInstance.STATE_ABORTED, pi.getState());
		Assert.assertEquals("90277",
				((WorkflowProcessInstance) pi).getOutcome());
		Rejection rejection = (Rejection) session
				.getObjects(new ClassObjectFilter(Rejection.class)).iterator()
				.next();
		Assert.assertNotNull(rejection);
		Assert.assertEquals("Risk > 500", rejection.getReason());
	}

	
	private Policy getTestRequest() {
		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy = new Policy(new Date(), "Auto", 2010, 0, 0, driver);
		return policy;
	}
}
