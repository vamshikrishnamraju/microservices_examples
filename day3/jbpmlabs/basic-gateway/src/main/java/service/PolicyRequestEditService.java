package service;

import model.Policy;
import model.Rejection;
import model.RejectionLevel;

public class PolicyRequestEditService {

	public void editRequest(Policy policy) {
		if (policy == null) {
			throw new RuntimeException("no policy request found");
		}

		if (policy.getDriver() == null) {
			policy.setRejection(new Rejection("No driver information given",
					RejectionLevel.FATAL));
			return;
		}

		if (policy.getVehicleYear() < 1950) {
			policy.setRejection(new Rejection(
					"We do not insure vehicles made before 1950",
					RejectionLevel.ERROR));
			return;
		}

		if (policy.getDriver().getAge() < 15) {
			policy.setRejection(new Rejection(
					"We do not insure drivers < 15 years old",
					RejectionLevel.ERROR));
			return;
		}

		policy.setRejection(null);
	}
}
