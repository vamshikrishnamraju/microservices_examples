package ex01_bean.ex03_5_classpath;

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
      // Classpath*: Prefix   
	  // ApplicationContext ctx = new ClasspathXmlapplication("classpath*:applicationContext.xml");
	   
    // Ant-Style paths
	   
     ApplicationContext factory = new ClassPathXmlApplicationContext("/ex01_bean/ex03_5_classpath/*Context.xml");
     
      // Now, retrieve the property values set via the setXXX methods on
      // instantiation of the JavaBean by the Spring Bean Factory
      Bean1 bean1a = (Bean1) factory.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.toString());
      System.out.println("String property: " + bean1a.getName());
      System.out.println("Int property: " + bean1a.getNo());
      System.out.println("List property: " + bean1a.getHobbies());
      
      Bean1 bean2a = (Bean1) factory.getBean("bean2");
      System.out.println("Retrieved Bean1: " + bean2a.toString());
      System.out.println("String property: " + bean2a.getName());
      System.out.println("Int property: " + bean2a.getNo());
      System.out.println("List property: " + bean2a.getHobbies());
      System.out.println("Done");
      
   }
   
}
