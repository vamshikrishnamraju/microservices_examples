/*
 * Created on 09-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ex02_aop.ex01_simpleadvice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;


public class SimpleBeforeAdvice implements MethodBeforeAdvice
{
   
   public void before(Method method, Object[] args, Object target)
      throws Throwable
   {
      System.out.println("BeforeAdvice: Before " + method);
   }
   
}
