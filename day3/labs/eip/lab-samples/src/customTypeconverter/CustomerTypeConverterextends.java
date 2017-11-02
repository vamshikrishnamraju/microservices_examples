package customTypeconverter;

import org.apache.camel.Exchange;
import org.apache.camel.support.TypeConverterSupport;

public class CustomerTypeConverterextends extends TypeConverterSupport {
 
    @SuppressWarnings("unchecked")
    public <T> T convertTo(Class<T> type, Exchange exchange, Object value) {
        // converter from value to the MyOrder bean
        Customer cust = new Customer();
        cust.setId(Integer.parseInt(value.toString()));
        return (T) cust;
    }
}