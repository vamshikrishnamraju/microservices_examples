
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import demo.Order;




/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	 String inhouse = "Lab-filter message";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new ChoiceRouteBuilder());
       
        context.start();
        Order order = new Order();
        
        order.setPrice(500);
        
        ProducerTemplate template = context.createProducerTemplate();
  
        template.sendBodyAndHeader("direct:start", order,"colour","white");
        
            }

}
