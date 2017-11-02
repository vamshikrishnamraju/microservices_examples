package policy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;
import model.Driver;
import model.Policy;

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

public class PolicyAccountingTest {

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
	public void testGetTotal() {
		
		List<Policy> policies = getTestRequest();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("policies", policies);
		params.put("total", (Integer) 0);
		params.put("totalDiscount", (Integer) 0);
		
		ProcessInstance pi = session.startProcess("policy.Accounts", params);
		
		Assert.assertEquals(ProcessInstance.STATE_COMPLETED, pi.getState());
		
		Integer total = (Integer) ((WorkflowProcessInstance)pi).getVariable("total");
		Integer totalDiscount = (Integer) ((WorkflowProcessInstance)pi).getVariable("totalDiscount");
		Assert.assertEquals((Integer) 2495, total);
		Assert.assertEquals((Integer) 53, totalDiscount);
	}
	
	private List<Policy> getTestRequest() {
		List<Policy> policies = new ArrayList<Policy>(3);
		
		Driver driver1 = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		Policy policy1 = new Policy(new Date(), "Auto", 2012, 455, 45, driver1);
		policies.add(policy1);
		
		Driver driver2 = new Driver("Jane Doe", 18, "555124444", "C125999", 1,
				2, 680);
		Policy policy2 = new Policy(new Date(), "Auto", 2001, 785, 8, driver2);
		policies.add(policy2);
		
		Driver driver3 = new Driver("Mark Smith", 35, "444132222", "C839022", 0,
				3, 450);
		Policy policy3 = new Policy(new Date(), "Auto", 1998, 1255, 0, driver3);
		policies.add(policy3);
		
		return policies;
	}
}
