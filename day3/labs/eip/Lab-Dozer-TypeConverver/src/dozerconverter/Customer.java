package dozerconverter;


public class Customer {
    private String firstName;
    private String lastName;
    private String street;
    private String zip;
 
    public Customer() {}
 
    public Customer(String firstName, String lastName, String zip, String street) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.zip = zip;
        this.street = street;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
 
}