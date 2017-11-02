package ex01_bean.ex09_property_EL;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class ExampleBeanPostProcessor implements BeanPostProcessor
{

   /* (non-Javadoc)
    * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
    */
   public Object postProcessBeforeInitialization(Object bean, String beanName)
         throws BeansException
   {
	   System.out.println("postProcessBeforeInitialization on " + beanName + " (" + bean.toString() + ")");
	    
	   if(bean instanceof Bean2)
	   {
	   Bean2 b2 = (Bean2) bean;
       System.out.println("PP##### " + b2.getNo() + "  "+ b2.getName());
	   }
      return bean;
   }


   /* (non-Javadoc)
    * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
    */
   public Object postProcessAfterInitialization(Object bean, String beanName)
         throws BeansException
   {
      System.out.println("postProcessAfterInitialization on " + beanName + " (" + bean.toString() + ")");
      return bean;
   }

}
