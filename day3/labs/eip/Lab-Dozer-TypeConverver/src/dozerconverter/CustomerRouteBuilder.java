package dozerconverter;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class CustomerRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	     
     
    public void configure() {

     //  from("direct:test").log("body : ${body}").bean(new CustomerProcessor());

    	from("direct:test").convertBodyTo(dozerconverter.domain.Customer.class).log("body : ${body.address.streetName}");
    } 
}
