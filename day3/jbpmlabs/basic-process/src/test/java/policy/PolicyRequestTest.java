package policy;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Persistence;

import junit.framework.Assert;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
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
import org.kie.internal.runtime.manager.context.EmptyContext;

public class PolicyRequestTest {

	private KieSession session;
	private RuntimeManager manager;

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
		ProcessInstance pi = session.startProcess("policy.Request", params);
		Assert.assertEquals(pi.getState(), ProcessInstance.STATE_COMPLETED);
	}

}
