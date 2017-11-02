package demo;
import java.util.Iterator;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;


public class Main {
  public static void main(String[] args) throws Exception {
	  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	  
    System.out.println("####");
    Session session = sessionFactory.openSession();

 
    
  //  System.out.println(survey.getId());
    Survey survey = (Survey) session.get(Survey.class, new Long(1));
    System.out.println(survey.getName());

    
    Session session2 = sessionFactory.openSession();
    Survey survey2 = (Survey) session2.get(Survey.class, new Long(1));
    System.out.println(survey2.getName());

    List query = session.createQuery("from Survey where name='abc'").setCacheable(true).list();
   
    for (Iterator it = query.iterator(); it.hasNext();)
    {
    	Survey ser = (Survey) it.next();
    	System.out.println("Iterator..Survey "+ ser.getLast());
    
    }
    
	try {
			    Thread.sleep(60 * 1000); 
		} 
	catch(final InterruptedException e) {
			    
			}
    List query2 = session2.createQuery("from Survey where name='abc'").setCacheable(true).list();
    
    for (Iterator it2 = query2.iterator(); it2.hasNext();)
    {
    	Survey ser = (Survey) it2.next();
    	System.out.println("Iterator..Survey "+ ser.getLast());
    
    }
    //  Query query = session.createQuery("from Survey");
 
   /* Cache playerCache = (Cache) CacheManager.getInstance().getCache("demo.Survey");

 playerCache.getMemoryStoreSize();
    */
    session.close();
  //  hibernateUtil.checkData("select * from survey");
  }
}
