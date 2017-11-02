package exF;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class TransformRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

      
    	
        from("file:src/data3?noop=true")
       // .convertBodyTo(String.class)
              .log("request : ${body}")
              .to("xquery:myTransform.xquery")
              .log("response : ${body}");

}
}
