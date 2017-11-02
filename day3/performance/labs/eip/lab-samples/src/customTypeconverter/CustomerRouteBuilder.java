package customTypeconverter;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class CustomerRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	     
     
    public void configure() {

       // from("direct:start").log("${body}");
        
     //from("direct:start").log("${body.id}");
    
    	
    	from("direct:start").log("Before : ${body}").convertBodyTo(Customer.class).log("After : ${body}");
         	
} 
}
