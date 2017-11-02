/*
 * Created on 09-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ex02_aop.ex04_introductions;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;


public class LockMixin extends DelegatingIntroductionInterceptor
   implements Lockable
{
   
   public void lock()
   {
      m_locked = true;
   }
   
   public void unlock()
   {
      m_locked = false;
   }

   public boolean locked()
   {
      return m_locked;
   }
   
   
   public Object invoke(MethodInvocation mi)
      throws Throwable
   {
      if (locked() && mi.getMethod().getName().startsWith("set"))
      {
         throw new IllegalStateException("Locked. Cannot call " + mi.getMethod());
      }

      return super.invoke(mi);
   }

   private boolean m_locked;
   
}
