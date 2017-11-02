package demo;

import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.services.client.api.RemoteJmsRuntimeEngineFactory;

public class JMSClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InitialContext remoteInitialContext  = getRemoteInitialContext("localhost", "admin", "admin_123");

	  int maxTimeoutSecs = 5;

	  RemoteJmsRuntimeEngineFactory remoteJmsFactory 

	    =  RemoteJmsRuntimeEngineFactory.newBuilder().addDeploymentId("org.kie.example:project1:1.0.0-SNAPSHOT")
	      .addRemoteInitialContext(remoteInitialContext)
	      .addUserName("admin")
	      .addPassword("admin_123")
	     .build();


	  // Interface with JMS api

	  RuntimeEngine engine = remoteJmsFactory.newRuntimeEngine();

	  KieSession ksession = engine.getKieSession();

	  ProcessInstance processInstance = ksession.startProcess("project1.ok");

	  long procId = processInstance.getId();

	

	}


	private static InitialContext getRemoteInitialContext(String jbossServerHostName, String user, String password) { 

	  // Configure the (JBoss AS 7/EAP 6) InitialContextFactory

	  Properties initialProps = new Properties();

	  initialProps.setProperty(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");

	  initialProps.setProperty(InitialContext.PROVIDER_URL, "remote://"+ jbossServerHostName + ":4447");

	  initialProps.setProperty(InitialContext.SECURITY_PRINCIPAL, user);

	  initialProps.setProperty(InitialContext.SECURITY_CREDENTIALS, password);


	  for (Object keyObj : initialProps.keySet()) {

	    String key = (String) keyObj;

	    System.setProperty(key, (String) initialProps.get(key));

	  }

	  

	  // Create the remote InitialContext instance

	  try {

	    return new InitialContext(initialProps);

	  } catch (NamingException e) {

	    throw new RuntimeException("Unable to create " + InitialContext.class.getSimpleName(), e);

	  }

	}
	}


