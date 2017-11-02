
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;




/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	 String inhouse = "Lab-Wiretap message";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new ChoiceRouteBuilder());
       
        context.start();
        
        ProducerTemplate template = context.createProducerTemplate();
  
        template.sendBody("direct:start", "z");  
        template.sendBody("direct:start", "a");     
        template.sendBody("direct:start", "m");     
            }

}
