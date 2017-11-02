package service;

import model.Driver;
import model.Policy;

public class NotificationService {

	public String notifyApproved(Policy policy) {
		StringBuffer sb = new StringBuffer(300);
		
		sb.append("Dear " + policy.getDriver().getDriverName() + ",\n");
		sb.append("Thank you for applying for an insurance policy with us.\n");
		sb.append("We are happy to inform you that your application has been approved.\n");
		sb.append("A policy will be issued within two business days.  ");
		sb.append("The cost of your policy is $" + policy.getPrice() + ".\n");
		sb.append("Thank you for choosing us.\n\n");
		sb.append("Sincerely,\nInsurance Masters");
		
		return sb.toString();
	
	}
	
	public String notifyRejected(Driver driver) {
		StringBuffer sb = new StringBuffer(300);
		
		sb.append("Dear " + driver.getDriverName() + ",\n");
		sb.append("Thank you for applying for an insurance policy with us.\n");
		sb.append("We regret to inform you that your application has been denied.\n");
		sb.append("Thank you for applying with us.\n\n");
		sb.append("Sincerely,\nInsurance Masters");
		
		return sb.toString();
	
	}
}
