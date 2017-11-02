

import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

        // here is a sample which processes the input files
        // (leaving them in place - see the 'noop' flag)
        // then performs content based routing on the message using XPath
    	
    //	BeanA bean = new BeanA();
    	
    	DisplayProcessor proc = new DisplayProcessor();
        from("file:src/data?noop=true")
       .to("xslt:user.xsl")
        .process(proc)
        .to("file:src/data2");
        
           
    }

}
