package dozerconverter;

public class CustomerProcessor {

	public void processCustomer(dozerconverter.domain.Customer customer) {
	       
		System.out.println("Result : " + customer.getAddress().getStreetName());
	    }
	
}
