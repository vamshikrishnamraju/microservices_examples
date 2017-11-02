package ex01_bean.ex08_messages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Create the Application Context
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex08_messages/ApplicationContext.xml");

      // Retrieve the Bean
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      
      // Retrieve the localised message
      // Note: The MessageSource provided to the Bean is configured in
      //       ApplicationContext.xml
      String strVal = bean1a.getMessage();
      System.out.println("Message: " + strVal);
      
      System.out.println("Done");
   }
   
}
