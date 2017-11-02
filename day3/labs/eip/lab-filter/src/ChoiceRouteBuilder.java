
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /** Simple expressions 
     * .simple("${in.header.colour} == 'red'")
     * simple("${body.address.zip} > 1000")
     */
	
    public void configure() {

    	from("direct:start")
    	//.filter(header("colour").isEqualTo("red"))
      // .filter().simple("${in.header.colour} == 'red'")  
    .filter().simple("${body.price} > '1000' ")
    	.to("direct:b");
    	
    	
    	
    	from("direct:b")
    	.log("final message ${body}");
    	
    	
    
    	
    	
    }

}
