package demo;



import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public class onetomany_set_preparedata {
  public static void main(String[] args) {
   	   SessionFactory sessionFactory;
	  sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    
    Transaction tx = session.beginTransaction();


        Event event1 = new Event();
        event1.setName("Hibernate Training");
        event1.setSpeakers(new HashSet());
        event1.getSpeakers().add(new Speaker("John", "Smith", event1));
        System.out.println("Check me" + event1.getSpeakers());
        event1.getSpeakers().add(new Speaker("Dave", "Smith", event1));
        event1.getSpeakers().add(new Speaker("Joan", "Smith",event1));
        session.save(event1);
        
        Event event2 = new Event();
        event2.setName("Spring Training");
        event2.setSpeakers(new HashSet());
        event2.getSpeakers().add(new Speaker("ram", "rao",event2));
        System.out.println("Check me" + event2.getSpeakers());
        event2.getSpeakers().add(new Speaker("jan", "mahesh",event2));
        event2.getSpeakers().add(new Speaker("david", "boss",event2));
        session.save(event2);
        
        Event event3 = new Event();
        event3.setName("Ejb3 Training");
        event3.setSpeakers(new HashSet());
        event3.getSpeakers().add(new Speaker("ram", "sagar",event3));
        System.out.println("Check me" + event3.getSpeakers());
        event3.getSpeakers().add(new Speaker("smith", "ok",event3));
        event3.getSpeakers().add(new Speaker("sree", "hai",event3));
        session.save(event3);
        
        tx.commit();
        session.close();
  }
}