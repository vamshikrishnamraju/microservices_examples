package ex01_bean.ex07_appcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Create the Application Context
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex07_appcontext/ApplicationContext.xml");
      
      // Retrieve Beans through it, just like a Bean Factory
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.getString());
      
      System.out.println("Done");
   }
   
}
