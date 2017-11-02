package ex01_bean.ex09_property_EL2;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ex01_bean.ex09_property_EL2.Spring3HelloWorldConfig;


public class Main
{

   public static void main(String[] args)
      throws Exception
   {
	   System.setProperty("favorite.languare", "java");

	  
      // Create the Application Context
	   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
			   Spring3HelloWorldConfig.class); 
      // Retrieve the Bean
      Bean1 bean1a = (Bean1) context.getBean("bean1");
      System.out.println("Retrieved Bean1: " + bean1a.getText());
      System.out.println("Retrieved Bean1: " + bean1a.getVersion());
   }
   
}
