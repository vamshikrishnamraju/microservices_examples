package ex02_aop.ex02_proxyfactorybean;




public class Bean1 implements BeanInterface2
{
	
	float amt;
	
	public Bean1()
	{
		
	}
	
	public void disp()
	{
		System.out.println("Display method invoked");
	}

   public void setString(String val)
   {
      m_strVal = val;
   }
   
   public String getString()
   {
      return m_strVal;
   }
   
   public int forceException(String val)
   {
      return val.length();
   }
   
   private String m_strVal;
}
