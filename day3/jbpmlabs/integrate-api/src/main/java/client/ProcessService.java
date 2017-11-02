package client;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.internal.event.KnowledgeRuntimeEventManager;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;

import bpm.RecordingProcessEventListener;

/**
 * Encapsulates a process instance and provides service methods to manipulate
 * that instance.
 * 
 * @author Red Hat Training
 * 
 */
public class ProcessService {

	private ProcessInstance pi;
	private TaskService taskService;
	private KieSession session;
	private RecordingProcessEventListener listener;
	private KnowledgeRuntimeLogger logger;

	public ProcessService(TaskService taskService, KieSession session) {
		this.taskService = taskService;
		this.session = session;
		// Add a process event listener to capture state information
		// while the process is active
		listener = new RecordingProcessEventListener();
		session.addEventListener(listener);
	}

	public long start(String procName, Map<String, Object> params) {

		pi = session.startProcess(procName, params);

		return pi.getId();
	}

	public void logOn() {
		logger = KnowledgeRuntimeLoggerFactory
				.newConsoleLogger((KnowledgeRuntimeEventManager) session);
	}

	public void freeLog() {
		if (logger != null)
			logger.close();
	}

	public String getStatus() {
		if (pi == null)
			throw new IllegalStateException("No process has started.");
		int state = 0;
		if (session.getProcessInstance(pi.getId()) == null)
			state = listener.getStatus();
		else
			state = pi.getState();

		switch (state) {
		case ProcessInstance.STATE_ABORTED:
			return "Aborted with error code: " + listener.getErrorCode();
		case ProcessInstance.STATE_ACTIVE:
			return "Active";
		case ProcessInstance.STATE_COMPLETED:
			return "Completed";
		case ProcessInstance.STATE_PENDING:
			return "Pending";
		case ProcessInstance.STATE_SUSPENDED:
			return "Suspended";
		default:
			return "Unknown";
		}
	}

	public Task[] getTasks() {
		List<Long> taskIds = taskService
				.getTasksByProcessInstanceId(pi.getId());

		Task[] tasks = new Task[taskIds.size()];
		int i = 0;
		for (Long id : taskIds) {
			tasks[i++] = taskService.getTaskById(id);
		}

		return tasks;
	}

	public void startTask(long taskId, String username) {
		taskService.claim(taskId, username);
		taskService.start(taskId, username);
	}

	public void completeTask(long taskId, String username,
			Map<String, Object> params) {
		taskService.complete(taskId, username, params);
	}

	public Map<String, Object> getVars() {
		return listener.getVariables();
	}

	public Object getVar(String varName) {
		return listener.getVariable(varName, Object.class);
	}

	public Collection<? extends Object> getFacts() {
		return session.getObjects();
	}

	public boolean isStarted() {
		return pi != null;
	}
}
