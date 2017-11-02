package exF;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;


import exB.CSVRouteBuilder;


/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    
    	 CamelContext context = new DefaultCamelContext();
    	 
         context.addRoutes(new TransformRouteBuilder());
        
         context.start();
      
    }

}
