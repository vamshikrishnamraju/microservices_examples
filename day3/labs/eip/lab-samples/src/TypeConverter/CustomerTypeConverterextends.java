package TypeConverter;

import org.apache.camel.Exchange;
import org.apache.camel.support.TypeConverterSupport;

public class CustomerTypeConverterextends extends TypeConverterSupport {
 
    @SuppressWarnings("unchecked")
    public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {

    	PersonDocument entity = (PersonDocument) value;
    	 CustomerEntity customer = new CustomerEntity();
        customer.setFirstName(entity.getFirstName());
        customer.setSurname(entity.getLastName());
        customer.setCity(entity.getCity());

        
        return (T) customer;
    }
}