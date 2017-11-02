package model;

import java.util.Date;

public class Policy implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private Driver driver;
	private String policyType;
	private Integer price;
	private Integer priceDiscount;
	private Date requestDate;
	private Integer vehicleYear;
	private Integer risk;

	public Policy() {
		
	}

	public Policy(Date requestDate, String policyType,
			Integer vehicleYear, Integer price,
			Integer priceDiscount, Driver driver) {
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

	public String getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getPriceDiscount() {
		return this.priceDiscount;
	}

	public void setPriceDiscount(Integer priceDiscount) {
		this.priceDiscount = priceDiscount;
	}

	public Date getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Integer getVehicleYear() {
		return this.vehicleYear;
	}

	public void setVehicleYear(Integer vehicleYear) {
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
		sb.append(",risk=" + this.risk);
		sb.append("\n");
		
		return sb.toString();
	}

	public Integer getRisk() {
		return risk;
	}

	public void setRisk(Integer risk) {
		this.risk = risk;
	}
}