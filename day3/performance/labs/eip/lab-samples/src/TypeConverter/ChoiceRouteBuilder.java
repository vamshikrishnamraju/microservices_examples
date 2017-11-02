package TypeConverter;

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

    	
     /*   from("file:src/data?move=.done")
        .tracing()
        .convertBodyTo(String.class)
     //  .convertBodyTo(Document.class)
     //   .convertBodyTo(byte[].class)
        .log("before: ${body}");
     */
        
    
        from("file:src/data?move=.done")
        .tracing()
          .convertBodyTo(PersonDocument.class)
         .convertBodyTo(CustomerEntity.class)
        .log("before: ${body.firstName}");
        
        
             
        
        
           /** .choice()
                .when(xpath("/person/city = 'London'"))
                    .to("file:target/messages/uk")
                .otherwise()
                    .to("file:target/messages/others");**/
    }

}
