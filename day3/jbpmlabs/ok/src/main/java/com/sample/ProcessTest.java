package com.sample;

import java.util.Properties;

import javax.persistence.Persistence;

import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

/**
 * This is a sample file to test a process.
 */
public class ProcessTest  {


	
	
	public static void main(String args[]) {
		
		 KieSession session;
		 RuntimeManager manager;
		

		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory
				.get()
				.newClasspathKmoduleDefaultBuilder("policyBase", "insurance")
				.entityManagerFactory(
						Persistence
								.createEntityManagerFactory("org.jbpm.persistence.jpa"))
				.get();
		
		manager = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(environment);
		// Get KieSession
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();
		
		ProcessInstance pi = session.startProcess("com.sample.bpmn.hello");
		session.fireAllRules();
		
	
	
	}

}