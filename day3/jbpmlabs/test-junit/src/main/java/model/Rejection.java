package model;

public class Rejection implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private String reason;
	private RejectionLevel level;

	public Rejection() {
	}

	public Rejection(String reason) {
		this.reason = reason;
		this.level = RejectionLevel.NONE;
	}

	public Rejection(String reason, RejectionLevel level) {
		this.reason = reason;
		this.level = level;
	}

	public java.lang.String getReason() {
		return this.reason;
	}

	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}

	public RejectionLevel getLevel() {
		return level;
	}

	public void setLevel(RejectionLevel level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(100);
		sb.append("Rejection => reason=" + this.reason);
		sb.append(", level=" + this.level);
		sb.append("\n");
		
		return sb.toString();
	}
}