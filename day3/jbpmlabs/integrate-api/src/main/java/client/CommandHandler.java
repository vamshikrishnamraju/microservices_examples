package client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import model.Driver;
import model.Policy;

import org.kie.api.task.model.Task;
import org.kie.api.task.model.User;

public class CommandHandler {

	private ProcessService service;

	public CommandHandler(ProcessService service) {
		this.service = service;
	}

	public String execute(String command) {
		if ("".equals(command.trim())) {
			help();
			return "OK";
		}

		StringTokenizer tokenizer = new StringTokenizer(command, " ");
		String[] args = new String[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			args[i++] = tokenizer.nextToken();
		}

		String keyword = args[0];

		if ("start".equals(keyword)) {
			if (service.isStarted())
				return "A process is already started.";

			if (args.length != 2) {
				help();
				return "OK";
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("request", CommandHandler.generatePolicyRequest());
			return "Process ID is " + service.start(args[1], params);
		} else if ("status".equals(keyword)) {
			if (!service.isStarted())
				return "No process has started.";
			return "Process Status:  " + service.getStatus();
		} else if ("tasks".equals(keyword)) {
			if (!service.isStarted())
				return "No process has started.";
			Task[] tasks = service.getTasks();
			StringBuffer sb = new StringBuffer(100);
			for (Task task : tasks) {
				User owner = task.getTaskData().getActualOwner();
				String status = task.getTaskData().getStatus().toString();
				sb.append(task.getId() + ": "
						+ task.getNames().iterator().next().getText());
				if (owner != null)
					sb.append(" Owner: " + owner.getId());
				sb.append(" Status: " + status);
				sb.append("\n");
			}
			return sb.toString();
		} else if ("start-task".equals(keyword)) {
			if (!service.isStarted())
				return "No process has started.";
			if (args.length != 3) {
				help();
				return "OK";
			}
			long taskId = Long.valueOf(args[1]);
			String username = args[2];
			service.startTask(taskId, username);
		} else if ("complete-task".equals(keyword)) {
			if (!service.isStarted())
				return "No process has started.";
			if (args.length < 3) {
				help();
				return "OK";
			}
			long taskId = Long.valueOf(args[1]);
			String username = args[2];
			Map<String, Object> params = new HashMap<String, Object>();
			for (int j = 3; j < args.length; j++) {
				String[] pair = args[j].split("=");
				params.put(pair[0], pair[1]);
			}
			service.completeTask(taskId, username, params);
		} else if ("vars".equals(keyword)) {
			for (Entry<String, Object> var : service.getVars().entrySet()) {
				System.out.println(var.getKey() + ":  " + var.getValue());
			}
		} else if ("var".equals(keyword)) {
			System.out.println(service.getVar(args[1]));
		} else if ("log".equals(keyword)) {
			service.logOn();
		} else if ("facts".equals(keyword)) {
			for (Object fact : service.getFacts()) {
				System.out.println(fact);
			}
		} else if ("end".equals(keyword)) {
			service.freeLog();
		} else
			help();

		return "OK";
	}

	private void help() {

		System.out.println("start process-name");
		System.out.println("status");
		System.out.println("tasks");
		System.out.println("start-task task-id username");
		System.out.println("complete-task task-id username key=value...");
		System.out.println("vars");
		System.out.println("var var-name");
		System.out.println("facts");
		System.out.println("log");
		System.out.println("end");
	}

	public static Policy generatePolicyRequest() {
		// TODO make available the ability to create objects from command
		// line

		Driver driver = new Driver("John Doe", 46, "555125555", "C125678", 0,
				0, 720);
		return new Policy(new Date(), "Auto", 2010, 0, 0, driver);
	}
}
