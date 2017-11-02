package ex02_aop.ex05_programmatic;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;

import ex02_aop.ex01_simpleadvice.SimpleAroundAdvice;
import ex02_aop.ex04_introductions.LockMixinAdvisor;
import ex02_aop.ex04_introductions.Lockable;

public class Main
{

   public static void main(String[] args)
      throws Exception
   {
	   BeanInterface1 beanTarget = new Bean1();
      
      beanTarget.setString("hai");
      
      ProxyFactory factory = new ProxyFactory(beanTarget);
      factory.addAdvice(new SimpleAroundAdvice());
      
      
      BeanInterface1 bean1a = (BeanInterface1)factory.getProxy();
      bean1a.setString("Hello World");
      
   }
}
