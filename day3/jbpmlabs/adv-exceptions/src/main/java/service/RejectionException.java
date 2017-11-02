package service;

import model.Rejection;

public class RejectionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Rejection rejection;
	
	public RejectionException(Rejection rejection) {
		super(rejection.getReason());
		this.rejection = rejection;
	}

	public Rejection getRejection() {
		return rejection;
	}

}
