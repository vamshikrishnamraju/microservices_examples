package simple;

import org.apache.camel.builder.RouteBuilder;

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
    
       	
      //  from("direct:start").setHeader("test", constant("good")).log("${in.headers.test}");
        
    //	  from("direct:start").log("Before : ${body}").setHeader("test", constant("good")).transform().simple("finale value:${header.test}").log("After : ${body}");
        
    //	from("direct:start").setHeader("test", constant("ok")).filter().simple("${in.header.test} == 'good'").log("goodwork");

//  from("direct:start").setHeader("test", constant("ok")).filter().simple("${in.header.test} in 'good,bad'").log("goodwork");
    	
from("direct:start").setHeader("ok",constant(true)).choice().when(header("ok").isEqualTo(true)).log("correct").otherwise().log("wrong");
    	
 //simple("${in.header.title} contains 'Camel'")
//simple("${in.header.type} is 'java.lang.String'")
//simple("${in.header.title} contains 'Camel' or ${in.header.type'} == 'gold'")
   //from("direct:start").choice().when().simple("${header.customer} == 'GOLD'}").to("direct:gold").otherwise().to("direct:other");	
    	
} 
}
