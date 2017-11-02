package ex02_aop.ex04_introductions;




public class Bean1 implements BeanInterface1
{

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
