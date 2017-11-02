package model;

public class Driver implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private Integer age;
	private Integer creditScore;
	private String dlNumber;
	private String driverName;
	private Integer numberOfAccidents;
	private Integer numberOfTickets;
	private String ssn;

	public Driver() {
	}

	public Driver(String driverName, Integer age, String ssn, String dlNumber,
			Integer numberOfAccidents, Integer numberOfTickets,
			Integer creditScore) {
		this.driverName = driverName;
		this.age = age;
		this.ssn = ssn;
		this.dlNumber = dlNumber;
		this.numberOfAccidents = numberOfAccidents;
		this.numberOfTickets = numberOfTickets;
		this.creditScore = creditScore;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getCreditScore() {
		return this.creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public String getDlNumber() {
		return this.dlNumber;
	}

	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Integer getNumberOfAccidents() {
		return this.numberOfAccidents;
	}

	public void setNumberOfAccidents(Integer numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	public Integer getNumberOfTickets() {
		return this.numberOfTickets;
	}

	public void setNumberOfTickets(Integer numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("Driver => name=" + this.driverName);
		sb.append(",dl#=" + this.dlNumber);
		sb.append(",age=" + this.age);
		sb.append(",ssn=" + this.ssn);
		sb.append(",#acc=" + this.numberOfAccidents);
		sb.append(",#tkt=" + this.numberOfTickets);
		sb.append(",credit=" + this.creditScore);
		sb.append("\n");

		return sb.toString();
	}
}