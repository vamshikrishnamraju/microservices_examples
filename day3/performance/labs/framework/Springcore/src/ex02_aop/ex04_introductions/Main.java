package ex02_aop.ex04_introductions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex02_aop/ex04_introductions/ApplicationContext.xml");

      BeanInterface1 bean1a = (BeanInterface1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      String strVal = bean1a.getString();
      System.out.println("String val: " + strVal);
      
      bean1a.setString("it's not locked - i can set this.");
      Lockable lock = (Lockable)bean1a;
      lock.lock();
      try
      {
         bean1a.setString("it's locked - i can't set this.");
      }
      catch(Exception e)
      {
         System.out.println("Caught exception " + e);
      }

      System.out.println("Done");
   }
   
}
