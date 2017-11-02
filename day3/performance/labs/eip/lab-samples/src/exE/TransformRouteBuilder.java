package exE;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class TransformRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    	
    	HtmlBean bean = new HtmlBean();
    	
        from("file:src/data?noop=true")
              .transform().method(bean,"toHtml");

}
}
