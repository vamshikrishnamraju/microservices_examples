package ex01_bean.ex11_merge;

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

	  // Bean1 test = new Bean1();
     ApplicationContext factory = new ClassPathXmlApplicationContext("ex01_bean/ex11_merge/ApplicationContext.xml");
     
     Person person1 = (Person) factory.getBean("person01");
      
     List<Address>  add = person1.getAddress();
     
     for(Address a : add)
     {
    	 System.out.println("Address :" + a.getCity());
     }
   }
   
}
