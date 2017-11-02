package ex01_bean.ex09_propertyplaceholder;

import javax.annotation.PostConstruct;


public class Bean1 
{
   public void setString(String val)
   {
      m_strVal = val;
   }
   
   
   @PostConstruct
   public void init()
   {
	   System.out.println("in init " +m_strVal );
   }
   public String getString()
   {
      return m_strVal;
   }
   
   public void setInt(int val)
   {
      m_intVal = val;
   }

   public int getInt()
   {
      return m_intVal;
   }


   private String m_strVal;
   private int m_intVal;
}
