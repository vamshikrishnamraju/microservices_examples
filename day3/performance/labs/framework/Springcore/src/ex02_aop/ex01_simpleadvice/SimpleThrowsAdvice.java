/*
 * Created on 09-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ex02_aop.ex01_simpleadvice;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

import org.springframework.aop.ThrowsAdvice;


public class SimpleThrowsAdvice implements ThrowsAdvice
{

   public void afterThrowing(RemoteException e)
   {
      System.out.println("Throws Advice: Remote Exception " + e + " thrown");
   }

   public void afterThrowing(Method m, Object[] args, Object target, NullPointerException e)
   {
      System.out.println("Throws Advice: Null Pointer Exception " + e + " thrown in method " + m);
   }

   
}
