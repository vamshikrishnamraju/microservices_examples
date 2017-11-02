package jaxB;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

/**
 * A Camel Java DSL Router
 */
public class CSVRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
	     
     
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    
    	JaxbDataFormat jaxb = new JaxbDataFormat("jaxB.Payments" );
    	jaxb.setPrettyPrint( true );
    	
    	from("file:src/data4?noop=true")
    	.convertBodyTo( String.class )
    	.unmarshal(jaxb)
    	      .log("response : ${body}");

}
}
