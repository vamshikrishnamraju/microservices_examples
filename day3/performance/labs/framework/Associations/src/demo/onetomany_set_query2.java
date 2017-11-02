package demo;



import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

public class onetomany_set_query2 {
  public static void main(String[] args) {
   	   SessionFactory sessionFactory;
	  sessionFactory = new Configuration().configure().buildSessionFactory();
       Session session = sessionFactory.openSession();
    
           Query list = session.createQuery("from Event");
        	  
  
      Iterator iter = list.iterate();
      
      session.close();
      
      if (!iter.hasNext())
      {
          System.out.println("No Events to display.");
          return;
      }
      while (iter.hasNext())
      {
          System.out.println("New Event :");
           Event event = (Event) iter.next();
           System.out.println(event.getName());
           Iterator iter2 = event.getSpeakers().iterator();
           while(iter2.hasNext())
        		   {
        	         Speaker speaker = (Speaker) iter2.next();
        	         System.out.println(speaker.getFirstName());
        	         System.out.println(speaker.getLastName());
        		   }
      }
  }    
}
