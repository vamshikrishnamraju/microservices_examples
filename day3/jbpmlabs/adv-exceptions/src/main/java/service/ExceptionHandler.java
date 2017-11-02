package service;

import java.util.Map;

import org.kie.api.runtime.process.WorkItem;

public class ExceptionHandler {
	
	public final static String EXCEPTION_PARAM = "policy.service.error";

	public void handleException(WorkItem workItem) {
        
        Map<String, Object> params = workItem.getParameters();
        Throwable throwable = (Throwable) params.get(ExceptionHandler.EXCEPTION_PARAM);
        if (throwable.getCause() instanceof RejectionException) {
        	System.out.println("Policy rejected:  " + ((RejectionException) throwable.getCause()).getRejection().getReason());
        } else
        	throwable.printStackTrace();
		
	}
}
