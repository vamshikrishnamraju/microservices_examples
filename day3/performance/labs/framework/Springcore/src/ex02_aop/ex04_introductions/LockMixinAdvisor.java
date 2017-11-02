/*
 * Created on 09-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ex02_aop.ex04_introductions;

import org.springframework.aop.support.DefaultIntroductionAdvisor;


public class LockMixinAdvisor extends DefaultIntroductionAdvisor
{

   public LockMixinAdvisor()
   {
      super(new LockMixin(), Lockable.class);
   }
   
}
