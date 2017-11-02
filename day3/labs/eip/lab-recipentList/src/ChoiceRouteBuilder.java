
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	//staic recipient list
    	
    //	from("direct:start").multicast().to("direct:vendor1").to("direct:vendor2").to("direct:vendor3");
    	
    	
    	
    	
    	
   // from("direct:start").multicast().parallelProcessing().to("seda:vendor1", "seda:vendor2", "seda:vendor3");
    	
    	//dynamic recipient list
    	
   	from("direct:start").recipientList(
    	        header("recipientListHeader").tokenize(","));
    	
    	
    	from("seda:vendor1").log("vendor1");
     	from("seda:vendor2").log("vendor2");
     	from("seda:vendor3").log("vendor3");
    
    	  
  	
    	
    }

}
