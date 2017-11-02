package dozerconverter.domain;

public class Address {
    private String zipCode;
    private String streetName;
 
    public Address() {}
 
    public Address(String zipCode, String streetName) {
        this.zipCode = zipCode;
        this.streetName = streetName;
    }

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
 
    
}