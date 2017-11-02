package service;

import model.Policy;

public class PolicyService {

	public void processRequest(Policy policy) {

		if (policy.getRejection() != null) {
			throw new RejectionException(policy.getRejection());
		}

	}
}
