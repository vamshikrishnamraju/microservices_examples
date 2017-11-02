

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp2 {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	 String inhouse = "0000005555000001144120091209  2319@1108";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new SplitterBeanRoute());
       
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
  
        List customer = CustomerService.createCustomer();
        
        template.sendBody("direct:start", customer);
        
            }

}
