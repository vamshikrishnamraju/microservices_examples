package exA;


import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import exB.CSVRouteBuilder;


/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    
//        Main main = new Main();
//        main.enableHangupSupport();
//        main.addRouteBuilder(new ChoiceRouteBuilder());
//       
//        main.run(args);
//      
   // 	String message="great job";
    	
    	 CamelContext context = new DefaultCamelContext();
    	 
         context.addRoutes(new ChoiceRouteBuilder());
   
         context.start();
         
      //   ProducerTemplate template = context.createProducerTemplate();
         
       //  template.sendBody("direct:process",message);
        
         
         Thread.sleep(500000);
         
         
    }

}
