package ex01_bean.ex05_postprocessor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

 
public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Manually create the Spring Bean Factory
      ClassPathResource res = new ClassPathResource("ex01_bean/ex05_postprocessor/ApplicationContext.xml");
      XmlBeanFactory factory = new XmlBeanFactory(res);

      // Construct our simple Bean Post Processor and register with the Bean Factory
      BeanPostProcessor pp = new ExampleBeanPostProcessor();
      factory.addBeanPostProcessor(pp);

      // Construct a Spring Bean Factory Post Processor which allows for
      // values within ApplicationContext.xml to be driven by property files.
      //
      // Note: In this case, the example.properties file is used to retrieve
      //       values.  For example, the placeholder in the following
      //       line of ApplicationContext.xml...
      //         <value>${example.string}</value>
      //       is replaced automatically before the Beans are instantiated.
      //
      // Note: When using an ApplicationContext (more of this later), it is possible
      //       declaratively setup a PropertyPlaceholderConfigurer without code.
      //
      PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
      cfg.setLocation(new ClassPathResource("ex01_bean/ex05_postprocessor/example.properties"));
      cfg.postProcessBeanFactory(factory);

      // Retrieve the Beans as usual
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
     
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      String strVal = bean1a.getString();
      System.out.println("String property: " + strVal);
      int intVal = bean1a.getInt();
      System.out.println("Int property: " + intVal);
      
      Bean1 bean2a = (Bean1) factory.getBean("bean2");
      Bean1 bean3a = (Bean1) factory.getBean("bean3");
      System.out.println("Done");
   }
   
}
