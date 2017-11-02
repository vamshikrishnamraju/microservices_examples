package ex01_bean.ex06_factory;



public class ExampleInstanceFactory
{

   public ExampleInstanceFactory(String val)
   {
      m_strVal = val;
   }
   
   public Bean1 createBean()
   {
      return new Bean1(m_strVal);
   }
   
   private String m_strVal;   
}
