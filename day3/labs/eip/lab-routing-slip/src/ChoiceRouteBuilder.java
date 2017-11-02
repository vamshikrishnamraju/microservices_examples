
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	from("direct:a").routingSlip(header("myHeader"),"#").ignoreInvalidEndpoints();
    	
    	    	
    	from("direct:b")
    	.log("in B  message ${body}");
    	
    	
    	from("direct:c")
    	.log("in C message ${body}");
    	
    	
    	
    }

}
