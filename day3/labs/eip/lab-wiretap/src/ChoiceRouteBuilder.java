
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	from("direct:start")
           .wireTap("direct:wire")
            .newExchangeBody(constant("Bye World"))
        .newExchangeHeader("id", constant(123))
        .newExchangeHeader("date", simple("${date:now:yyyyMMdd}"))
        .to("direct:target");
    	
    	/** wireTap("direct:wire", false, new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody("Bye World");
                exchange.getIn().setHeader("foo", "bar");
            }    	*/
    	
    	
    	from("direct:wire")
    	.log("wiretap message ${body}");
    	
    	
    	from("direct:target")
    	.log("target message : ${body}");
    	
    	
    }

}
