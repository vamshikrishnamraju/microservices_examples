package bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.event.process.DefaultProcessEventListener;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;

public class RecordingProcessEventListener extends DefaultProcessEventListener {

	private Map<String, Object> variables = new HashMap<String, Object>();
	private int status = -1;
	private String errorCode;
	private List<String> pathExecuted = new ArrayList<String>();

	public RecordingProcessEventListener() {
	}

	@Override
	public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		String currentNode = event.getNodeInstance().getNodeName();
		if (currentNode != null && !"".equals(currentNode)) {
			pathExecuted.add(currentNode);
			System.out.println("Current Node:  "
					+ event.getNodeInstance().getNodeName());
		}
	}

	@Override
	public void afterProcessCompleted(ProcessCompletedEvent event) {
		status = event.getProcessInstance().getState();
		errorCode = ((WorkflowProcessInstance) event.getProcessInstance())
				.getOutcome();

	}

	@Override
	public void afterProcessStarted(ProcessStartedEvent event) {
		this.status = event.getProcessInstance().getState();
		org.jbpm.process.instance.ProcessInstance jpi = (org.jbpm.process.instance.ProcessInstance) event
				.getProcessInstance();
		VariableScopeInstance variableScope = (VariableScopeInstance) jpi
				.getContextInstance(VariableScope.VARIABLE_SCOPE);
		Map<String, Object> procvariables = variableScope.getVariables();
		for (Entry<String, Object> variable : procvariables.entrySet()) {
			this.variables.put(variable.getKey(), variable.getValue());
		}
	}

	@Override
	public void afterVariableChanged(ProcessVariableChangedEvent event) {

		variables.put(event.getVariableId(), event.getNewValue());

	}

	public int getStatus() {
		return this.status;
	}

	public List<String> getPath() {
		return this.pathExecuted;
	}

	public String graphPath() {
		boolean start = true;
		String path = "";
		for (String node : this.pathExecuted) {
			if (!start)
				path += " -> ";
			start = false;
			path += node;
		}
		return path;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public List<String> getVariableNames() {
		List<String> names = new ArrayList<String>();

		for (Entry<String, Object> entry : this.variables.entrySet())
			names.add(entry.getKey());

		return names;
	}

	public <T extends Object> T getVariable(String name, final Class<T> type) {
		Object value = this.variables.get(name);

		return type.cast(value);
	}

	public Map<String, Object> getVariables() {
		return this.variables;
	}

}