
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;



public class MainApp {

    /**
     This pattern enables you to route a message consecutively through a series  of processing steps, where the sequence of steps 
     is not known at design time.  The list of endpoints through which the message should pass is calculated 
     dynamically at run time.  Each time the message returns from an endpoint, the dynamic router calls back on a bean
     to discover the next endpoint in the route.
     */

	public static void main(String... args) throws Exception {
    	 String inhouse = "hi";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new ChoiceRouteBuilder());
       
        context.start();
        
        ProducerTemplate template = context.createProducerTemplate();
  
        template.sendBody("direct:start", "sb");
       
      template.sendBody("direct:start", "current");
        
        
        Thread.sleep(50000);
            }

}
