
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
        // use a bean as the dynamic router
        .dynamicRouter(method(DynamicRouterTest.class, "slip"));
    	
    	
    	from("direct:a").log("sedaA : ${body}").setBody(simple("current"));
   
    	
     	from("direct:b").log("sedaB : ${body}").setBody(simple("done2"));
     	from("direct:c").log("sedaC");
     	from("direct:d").log("sedaD");
     	from("direct:e").log("sedaE");
    	
    }

}
