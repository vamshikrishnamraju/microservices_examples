
import org.apache.camel.builder.RouteBuilder;


/**
 * A Camel Java DSL Router
 */
public class ChoiceRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {

    	from("direct:start").multicast().to("seda:vendor1", "seda:vendor2", "seda:vendor3");
    	 
    	
    	from("seda:vendor1").transform().simple("10000").to("seda:quoteAggregator");
    	from("seda:vendor2").transform().simple("20000").to("seda:quoteAggregator");
    	from("seda:vendor3").transform().simple("15000").to("seda:quoteAggregator");
    	 
    	from("seda:quoteAggregator")
    	  .aggregate(header("company"), new LowestQuoteAggregationStrategy()).completionTimeout(3000)
    	   .to("seda:result");
    	  
    	
    
    	from("seda:result")
    	.log("Message : ${body}");
    	
    	
    }

}
