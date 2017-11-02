package httpclient;

import org.apache.camel.*;
import org.apache.camel.component.http.HttpComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class HttpClient {

    public static void main(String ... args){
        CamelContext camelContext = new DefaultCamelContext();
      
        
        HttpComponent httpComponent = new HttpComponent();
        
       // HttpComponent httpComponent = new HttpComponent(); 
        
       camelContext.addComponent("http2", httpComponent);
        
        ProducerTemplate template = camelContext.createProducerTemplate();

        Exchange exchange = template.send("http://www.google.com/search", new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setHeader(Exchange.HTTP_QUERY, "hl=en&q=activemq");
            }
        });

        Message out = exchange.getOut();
        System.out.println("Response from http template is "+exchange.getOut().getBody());
        System.out.println("status header is "+out.getHeader(Exchange.HTTP_RESPONSE_CODE));
    }

}