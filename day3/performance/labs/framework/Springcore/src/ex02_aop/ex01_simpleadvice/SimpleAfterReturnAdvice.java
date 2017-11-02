/*
 * Created on 09-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ex02_aop.ex01_simpleadvice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;


public class SimpleAfterReturnAdvice implements AfterReturningAdvice
{
   
   public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
      throws Throwable
   {
      System.out.println("AfterReturnAdvice: Method " + method + " returned " + returnValue);
   }
   
}
