package simple;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class CustomerRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	     
     
    public void configure() {

        from("direct:start")
        .transform()
        .simple("${body} processed")
        .log("${body}");
        
 
       
         	
} 
}
