package ex01_bean.ex06_factory;

import org.springframework.beans.factory.FactoryBean;



public class ExampleFactoryBean implements FactoryBean
{
   /**
    * Factory constructor
    * 
    * Note: The arguments to this factory can be provided using standard
    *       Spring Bean definition constructs.
    */
   public ExampleFactoryBean(String val)
   {
      m_strVal = val;
   }

   // A simple implementation of the FactoryBean interface...
   
   public Object getObject() throws Exception
   {
      return new Bean1(m_strVal);
   }
   
   public Class getObjectType()
   {
      return Bean1.class;
   }
   
   public boolean isSingleton()
   {
      return false;
   }
   
   
   private String m_strVal;
   
}
