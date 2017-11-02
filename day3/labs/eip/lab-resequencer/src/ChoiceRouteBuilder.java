
import org.apache.camel.builder.RouteBuilder;


/*
 * Batch Resequencing : The following example shows how to use the
* batch-processing resequencer so that messages are sorted in order 
* of the body() expression. 
 */

public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	from("direct:start")
       .resequence().body()
       //.batch().size(300).timeout(4000L)
        .to("direct:result");
    	
    
    	from("direct:result")
    	.log("message : ${body}");
    	
    	
    }

}
