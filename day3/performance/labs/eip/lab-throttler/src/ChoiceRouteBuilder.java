
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	// will throttle messages all messages received on direct:a before being sent to direct:b
    	// ensuring that a maximum of 3 messages are sent in any 10 second window.
    	
  	from("direct:a").throttle(3).timePeriodMillis(10000).to("direct:b");
    	    	
    	    	from("direct:b")
    	    	.log("final message ${header.colour}");
    
    	    	  /*
    	Customer cs = new Customer();
    	
    	from("file:src/data")
    	.convertBodyTo(String.class)
    	//.bean(cs,"info")
    	//.split(body())
     .split(body(String.class).tokenize("\n"))
    	.to("seda:process");
    	
    	from("seda:process")
    	.log("message is ${body}");
    	*/
    	
    	
    
    	
    	
    }

}
