package ex01_bean.ex05_postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


public class ExampleBeanPostProcessor implements BeanPostProcessor
{

	float val=100.0f;
	
   /* (non-Javadoc)
    * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
    */
   public Object postProcessBeforeInitialization(Object bean, String beanName)
         throws BeansException
   {
	   val++;
	   Bean1 beanp = (Bean1) bean;
	   System.out.println("in example " + beanp.getString());
      System.out.println("postProcessBeforeInitialization on " + beanName + " (" + bean.toString() + ")" + val);
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
