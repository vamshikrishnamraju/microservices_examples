package demo;



import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

public class onetomany_set_list2 {
  public static void main(String[] args) {
   	   SessionFactory sessionFactory;
	  sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    
    
//List list = session.createQuery("from Event e inner join fetch e.speakers as s").list();
List list = session.createQuery("from Event, Speaker").list();

System.out.println("list size" + list.size());
  //session.close();
    
     displayObjectsList(list);  
   session.close();
        sessionFactory.close();
        }
        
  

      static public void displayObjectsList(List list)
  {
    	  
  
      Iterator iter = list.iterator();
      if (!iter.hasNext())
      {
          System.out.println("No Events to display.");
          return;
      }
      while (iter.hasNext())
      {
          System.out.println("New Event :");
           Object[] obj = (Object[]) iter.next();
           System.out.println("obj size" + obj.length);
           List test = Arrays.asList(obj);
           System.out.println("list size2" + test.size());
           Iterator testI = test.iterator();
           while(testI.hasNext())
           {
        	
           Object check = testI.next();
           
        	 if(check instanceof Event)
        	 {
        		 Event e = (Event) check;
        		 System.out.println(e.getName());
        	 }
        	 else
        	 {
        		 Speaker s  = (Speaker) check;
        		 System.out.println(s.getFirstName());
        	 }
         
      }
  }    
}
}