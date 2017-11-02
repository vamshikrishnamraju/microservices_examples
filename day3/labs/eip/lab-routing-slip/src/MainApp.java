
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;




/**
The Routing Slip from the EIP patterns allows you to route a message consecutively through a
series of processing steps where the sequence of steps is not known at design time and can 
vary for each message.
 */

public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	 String inhouse = "Lab-routing-slip message";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new ChoiceRouteBuilder());
       
        context.start();
        
        ProducerTemplate template = context.createProducerTemplate();
  
        template.sendBodyAndHeader("direct:a", inhouse,"myHeader","direct:c#direct:b");
        
        System.out.println("+++++++++++++");
        
        template.sendBodyAndHeader("direct:a", inhouse,"myHeader","direct:d#direct:c");
        
        Thread.sleep(5000);
            }

}
