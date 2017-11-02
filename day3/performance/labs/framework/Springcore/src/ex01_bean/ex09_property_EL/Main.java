package ex01_bean.ex09_property_EL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Create the Application Context
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex09_property_EL/ApplicationContext.xml");
      
      // Retrieve the Bean
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      
      // Retrieve the values assigned by the property file
      String strVal = bean1a.getName();
      System.out.println("String Value: " + strVal);
      int intVal = bean1a.getNo();
      System.out.println("Int Value: " + intVal);
      
    //  bean1a.setNo(500);
      Bean2 bean1b = (Bean2) factory.getBean("bean2");
      System.out.println("Retrieved Bean2: " + bean1b.toString());
      
      // Retrieve the values assigned by the property file
      String strVal2 = bean1b.getName();
      System.out.println("String Value: " + strVal2);
      int intVal2 = bean1b.getNo();
      System.out.println("Int Value: " + intVal2);
      
      
      
      System.out.println("Done");
   }
   
}
