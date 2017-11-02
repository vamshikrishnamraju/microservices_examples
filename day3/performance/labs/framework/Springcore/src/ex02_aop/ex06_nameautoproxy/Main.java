package ex02_aop.ex06_nameautoproxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * Created on 07-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author davidc
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex02_aop/ex06_nameautoproxy/ApplicationContext.xml");

      {
    	  BeanInterface1 bean1a = (BeanInterface1) factory.getBean("bean1");
         System.out.println("Retrieved Bean1: " + bean1a.toString());
         String strVal = bean1a.getString();
         System.out.println("String val: " + strVal);
      }

      {
    	  BeanInterface1 bean1b = (BeanInterface1) factory.getBean("proxybean1");
         System.out.println("Retrieved ProxyBean1: " + bean1b.toString());
         String strVal = bean1b.getString();
         System.out.println("String val: " + strVal);
      }

      {
    	  BeanInterface1 bean1c = (BeanInterface1) factory.getBean("proxybean2");
         System.out.println("Retrieved ProxyBean1: " + bean1c.toString());
         String strVal = bean1c.getString();
         System.out.println("String val: " + strVal);
      }
      
      System.out.println("Done");
   }
   
}
