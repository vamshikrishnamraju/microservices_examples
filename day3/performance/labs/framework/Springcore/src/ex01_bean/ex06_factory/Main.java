package ex01_bean.ex06_factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Manually create the Spring Bean Factory
      ClassPathResource res = new ClassPathResource("ex01_bean/ex06_factory/ApplicationContext.xml");
      XmlBeanFactory factory = new XmlBeanFactory(res);
      
      {
         // Retrieve JavaBean via static Factory
         // Note: Although the Factory is registered as "bean1" in ApplicationContext.xml,
         //       the Bean constructed by the Factory is returned; not the Factory itself
         Bean1 bean1a = (Bean1) factory.getBean("bean1");
         System.out.println("Retrieved Bean1: " + bean1a.toString());
         String strVal = bean1a.getString();
         System.out.println("String property: " + strVal);
      }
      
      {
         // Retrieve JavaBean via Factory instance
         Bean1 bean2a = (Bean1) factory.getBean("bean2");
         System.out.println("Retrieved Bean2: " + bean2a.toString());
         String strVal = bean2a.getString();
         System.out.println("String property: " + strVal);
      }

      {
         // Retrieve JavaBean via factory implementing the FactoryBean interface
         Bean1 bean3a = (Bean1) factory.getBean("bean3");
         System.out.println("Retrieved Bean3: " + bean3a.toString());
         String strVal = bean3a.getString();
         System.out.println("String property: " + strVal);
      }

      {
         // Just in case, you can retrieve the factory itself, by prepending the
         // Bean name with an &
         FactoryBean factoryBean = (FactoryBean) factory.getBean("&bean3");
         System.out.println("Retrieved Factory Bean: " + factoryBean.toString());
      }

      System.out.println("Done");
   }
   
}
