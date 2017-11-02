package ex02_aop.ex02_proxyfactorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      ApplicationContext factory = new ClassPathXmlApplicationContext("ex02_aop/ex02_proxyfactorybean/ApplicationContext.xml");

      {
    	  
    	  BeanInterface2 bean1p = (BeanInterface2) factory.getBean("bean1p");
    	  BeanInterface2 bean1a = (BeanInterface2) factory.getBean("bean1");
    	  
    	  bean1a.disp();
    	  
    	  System.out.println("#############");
    	  
    	  bean1p.disp();
    	  
    	  
      }
   
   }
}
   
