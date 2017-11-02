package TypeConverter;


import org.apache.camel.CamelContext;
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
    	
    	 CamelContext context = new DefaultCamelContext();
    	 
         context.addRoutes(new ChoiceRouteBuilder());
        
        context.getTypeConverterRegistry().addTypeConverter(  CustomerEntity.class,PersonDocument.class, new CustomerTypeConverterextends());
         
         
         context.start();
         
         Thread.sleep(50000);
         
         
    }

}
