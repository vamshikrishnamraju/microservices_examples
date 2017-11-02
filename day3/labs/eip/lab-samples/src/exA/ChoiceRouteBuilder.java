package exA;

import org.apache.camel.builder.RouteBuilder;
import org.w3c.dom.Document;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {


    	DisplayProcessor proc1 = new DisplayProcessor();
    	
    	from("file:src/data")
    	.convertBodyTo(String.class)
    	.log("original message : ${body}")
    	.to("seda:a")
    	.tracing()
    	.log("final message : ${body}");
    	
    	
    	from("seda:a")
    	.process(proc1)
    	.log("inside direct message : ${body}");
    	
    	
    	
    	
    	
    	
        

    }

}
