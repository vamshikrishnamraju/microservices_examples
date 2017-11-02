
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
    	 String inhouse = "Lab-loadblancer message";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
        context.addRoutes(new ChoiceRouteBuilder());
       
        context.start();
        
        ProducerTemplate template = context.createProducerTemplate();
  
        template.sendBodyAndHeader("direct:start", inhouse,"colour","black");
        template.sendBodyAndHeader("direct:start", inhouse,"colour","black");
        template.sendBodyAndHeader("direct:start", inhouse,"colour","black");
        template.sendBodyAndHeader("direct:start", inhouse,"colour","black");
        
            }

}
