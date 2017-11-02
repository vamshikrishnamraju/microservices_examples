package ex01_bean.ex02_setter_pool;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
      // Manually create the Spring Bean Factory

	   Bean1 test = new Bean1();
     ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex02_setter_pool/ApplicationContext.xml");
     
      // Now, retrieve the property values set via the setXXX methods on
      // instantiation of the JavaBean by the Spring Bean Factory
      Bean1 bean1a = (Bean1) factory.getBean("bean1pooltarget");

      System.out.println("Int property: " + bean1a.getNo());
  bean1a.setNo(100);
  
      
  Bean1 bean2a = (Bean1) factory.getBean("bean1SL");
  System.out.println("Int property: " + bean2a.getNo());
      

  Bean1 bean3a = (Bean1) factory.getBean("bean1SL");
  System.out.println("Int property: " + bean3a.getNo());
  
   }
   
}
