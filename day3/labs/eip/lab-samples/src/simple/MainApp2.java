package simple;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;




/**
 * A Camel Application
 */
public class MainApp2 {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new CustomerRouteBuilder());
       
        context.start();
        
        Thread.sleep(100000);
       // ProducerTemplate template = context.createProducerTemplate();
  
  //      Customer cust = new Customer();
       
    //    cust.setId(1);
      //  cust.setName("good");
        //template.sendBody("direct:start", cust);
        
            }

}
