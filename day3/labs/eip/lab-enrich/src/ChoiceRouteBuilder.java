
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

   // 	from("direct:start").setBody(body().append(" World!")).to("direct:target");
    	
  /** 	from("direct:start").process(new Processor() {
    	    public void process(Exchange exchange) {
    	        Message in = exchange.getIn();
    	        in.setBody(in.getBody(String.class) + " World!");
    	    }
    	}) */
    
    	from("direct:start")
    	  .pollEnrich("file:src/inbox?fileName=data.txt")
    	  .to("direct:result");
    	  
    	from("direct:result")
    	.log("message : ${body}");
    	
   /** 	from("direct:start")
    	  .enrich("direct:resource")
    	  .to("direct:target");
    	
  	
    	from("direct:resource")
    	.transform().simple("${body} final");
    	
    	
    	from("direct:target")
    	.log("target message : ${body}");
    	*/
    	
    }

}
