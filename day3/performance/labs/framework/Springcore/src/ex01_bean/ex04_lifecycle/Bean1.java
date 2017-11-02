package ex01_bean.ex04_lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Bean1
{
   public Bean1(String val)
   {
      m_strVal = val;
   }

   // Note: Init is called after property setters
   public void init()
   {
      System.out.println("Init callback called on " + this.toString() + " - String Property value: " + m_strVal);
   }

   public void close()
   {
      System.out.println("Destroy callback called on " + this.toString());
   }
   
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
   
   public void setInt(int val)
   {
      m_intVal = val;
   }

   private String m_strVal;
   private int m_intVal;
}
