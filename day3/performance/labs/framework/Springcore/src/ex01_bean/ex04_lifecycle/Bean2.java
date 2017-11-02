package ex01_bean.ex04_lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class Bean2 implements InitializingBean, DisposableBean
{
   public Bean2(String val)
   {
      m_strVal = val;
   }

   public void setInt(int val)
   {
      m_intVal = val;
   }
   
   /**
    * Implement InitializingBean callback method
    * 
    */
   
   @PostConstruct
   public void start()
   {
	   System.out.println("start method --- @PostConstruct");
   }
   
   
   
   @PreDestroy
   public void end()
   {
	   System.out.println("end method --- @PreDestroy");
   }
   
   public void afterPropertiesSet() throws Exception
   {
      System.out.println("Init callback called on " + this.toString() + " - String Property value: " + m_strVal);
   }

   /**
    * Implement DisposableBean callback method
    */
   public void destroy() throws Exception
   {
      System.out.println("Destroy callback called on " + this.toString());
   }


   private String m_strVal;
   private int m_intVal;
   
}
