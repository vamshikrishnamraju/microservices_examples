package client;

import java.util.Properties;
import java.util.Scanner;

import javax.persistence.Persistence;

import org.jbpm.services.task.identity.JBossUserGroupCallbackImpl;
import org.jbpm.test.JBPMHelper;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.TaskService;
import org.kie.internal.runtime.manager.context.EmptyContext;
import org.kie.internal.task.api.UserGroupCallback;

public class ProcessDriver {

	private static final ProcessDriver instance = new ProcessDriver();

	private KieSession session;
	private TaskService taskService;
	private RuntimeManager manager;

	public static void main(String[] args) {

		instance.startRuntime();

		instance.commandLoop();

		instance.shutdownRuntime();

		System.exit(0); // needed because of H2 server in background

	}

	private void commandLoop() {
		// A command session can only handle one process instance
		// The user must "end" the command session after they are
		// finished with a single process instance
		CommandHandler commandHandler = new CommandHandler(new ProcessService(
				taskService, session));
		// To read commands from console
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");

		System.out.print("Command please:  ");
		String command = scanner.next();

		// Execute commands until command "end" is given
		while (true) {
			try {
				System.out.println(commandHandler.execute(command));
			} catch (Exception e) {
				System.out.println(e.getClass().getSimpleName() + ": "
						+ e.getMessage());
				e.printStackTrace();
			}
			if ("end".equals(command))
				break;

			System.out.print("Command please:  ");
			command = scanner.next();
		}

		scanner.close();

		System.out.println("Shutting down...");
	}

	private void startRuntime() {
		// For testing, create an H2 DB Server
		JBPMHelper.startH2Server();
		// Create data source and register with JNDI
		JBPMHelper.setupDataSource();
		// Define users and group affiliations
		Properties properties = new Properties();
		properties.setProperty("joe", "");
		properties.setProperty("mary", "actuary");
		properties.setProperty("henry", "actuary");
		UserGroupCallback userGroupCallback = new JBossUserGroupCallbackImpl(
				properties);
		// Build a runtime environment with persistence
		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory
				.get()
				.newClasspathKmoduleDefaultBuilder("policyBase", "insurance")
				.entityManagerFactory(
						Persistence
								.createEntityManagerFactory("org.jbpm.persistence.jpa"))
				.userGroupCallback(userGroupCallback).get();
		// Create the Runtime Manager
		manager = RuntimeManagerFactory.Factory.get()
				.newPerRequestRuntimeManager(environment);
		// Get the task service
		taskService = manager.getRuntimeEngine(EmptyContext.get())
				.getTaskService();
		// Get the session
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();

	}

	private void shutdownRuntime() {

		// When we use a runtime manager we do not close the session
		if (manager != null) {
			manager.disposeRuntimeEngine(manager.getRuntimeEngine(EmptyContext
					.get()));
			manager.close();
			System.out.println("Runtime shutdown");
		}
	}

}
