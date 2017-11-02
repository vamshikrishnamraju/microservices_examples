package ex02_aop.ex03_advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex02_aop/ex03_advisor/ApplicationContext.xml");

      BeanInterface2 bean1a = (BeanInterface2) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      String strVal = bean1a.getString();
      System.out.println("String val: " + strVal);
      try
      {
         int i = bean1a.forceException(null);
      }
      catch(Exception e)
      {
      }

      System.out.println("Done");
   }
   
}
