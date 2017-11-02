package ex01_bean.ex05_postprocessor;


public class Bean1
{
	
	public Bean1()
	{
	}
	
	
   public Bean1(String val)
   {
      m_strVal = val;
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

   public void init()
   {
      System.out.println("Init callback called on " + this.toString() + " - String Property value: " + m_strVal);
   }

   private String m_strVal;
   private int m_intVal;
}
