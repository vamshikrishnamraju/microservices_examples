package dozerconverter;

import java.util.Arrays;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.converter.dozer.DozerTypeConverterLoader;
import org.apache.camel.impl.DefaultCamelContext;
import org.dozer.DozerBeanMapper;






/**
 * A Camel Application
 */
public class MainApp {

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
    	 String inhouse = "0000005555000001144120091209  2319@1108";
       
    	 CamelContext context = new DefaultCamelContext();
    	 
    	 context.addRoutes(new CustomerRouteBuilder());
    	 
    	 DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{"mapping.xml"}));
    	 new DozerTypeConverterLoader(context, mapper);
    	 
         context.start();
         
         ProducerTemplate template = context.createProducerTemplate();
         
    	 template.sendBody("direct:test",new dozerconverter.Customer("Bob", "Roberts", "12345", "1 Main st."));
    	 
            }

}
