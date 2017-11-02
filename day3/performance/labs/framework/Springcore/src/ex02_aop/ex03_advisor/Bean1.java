package ex02_aop.ex03_advisor;




public class Bean1 implements BeanInterface2
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
