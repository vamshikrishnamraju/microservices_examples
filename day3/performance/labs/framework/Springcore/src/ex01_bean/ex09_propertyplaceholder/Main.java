package ex01_bean.ex09_propertyplaceholder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Create the Application Context
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex09_propertyplaceholder/ApplicationContext.xml");
      
      // Retrieve the Bean
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      
      // Retrieve the values assigned by the property file
      String strVal = bean1a.getString();
      System.out.println("String Value: " + strVal);
      int intVal = bean1a.getInt();
      System.out.println("Int Value: " + intVal);
      
      System.out.println("Done");
   }
   
}
