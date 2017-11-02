
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	from("direct:start").loadBalance()
    	.roundRobin().to("direct:x", "direct:y", "direct:z");
    	
    	
    	from("direct:x")
    	.log("x message ${body}");
    	
    	
     	from("direct:y")
    	.log("y message ${body}");
    	
     	from("direct:z")
    	.log("z message ${body}");
    	
     	
    	
    	
    }

}
