package exB;

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
    
    //	OrderToCsvProcessor csvProcessor = new  OrderToCsvProcessor();
    //	HtmlBean bean = new HtmlBean();
    	
        from("direct:start")
        .log("before is : ${body}")
        .log("header is ${headers.Date}")
        .to("seda:end")
           .log("after  is : ${body}");
        
        
        from("seda:end")
        .transform()
        .simple("${body} great")
        .log("inside : ${body}");
        
        
            //  .process(csvProcessor)
              
                       //    .to("file://target/orders/received?fileName=report-${header.Date}.csv");

}
}
