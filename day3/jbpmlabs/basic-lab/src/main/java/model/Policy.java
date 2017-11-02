package model;

public class Policy implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private Driver driver;
	private java.lang.String policyType;
	private java.lang.Integer price = 0;
	private java.lang.Integer priceDiscount = 0;
	private java.util.Date requestDate;
	private java.lang.Integer vehicleYear;
	private boolean approved = false;

	public Policy() {
	}

	public Policy(java.util.Date requestDate, java.lang.String policyType,
			java.lang.Integer vehicleYear, java.lang.Integer price,
			java.lang.Integer priceDiscount, Driver driver) {
		this.requestDate = requestDate;
		this.policyType = policyType;
		this.vehicleYear = vehicleYear;
		this.price = price;
		this.priceDiscount = priceDiscount;
		this.driver = driver;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public java.lang.String getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(java.lang.String policyType) {
		this.policyType = policyType;
	}

	public java.lang.Integer getPrice() {
		return this.price;
	}

	public void setPrice(java.lang.Integer price) {
		this.price = price;
	}

	public java.lang.Integer getPriceDiscount() {
		return this.priceDiscount;
	}

	public void setPriceDiscount(java.lang.Integer priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public java.util.Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(java.util.Date requestDate) {
		this.requestDate = requestDate;
	}

	public java.lang.Integer getVehicleYear() {
		return this.vehicleYear;
	}

	public void setVehicleYear(java.lang.Integer vehicleYear) {
		this.vehicleYear = vehicleYear;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(200);
		sb.append("Policy => driver=" + ((driver == null) ? "None" : this.driver.getDriverName()));
		sb.append(",type=" + this.policyType);
		sb.append(",price=" + this.price);
		sb.append(",disc=" + this.priceDiscount);
		sb.append(",date=" + this.requestDate);
		sb.append(",vehyr=" + this.vehicleYear);
		sb.append(",approved=" + (approved ? "yes" : "no"));
		sb.append("\n");
		
		return sb.toString();
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}