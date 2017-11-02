package demo;



import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

public class onetomany_get {
  public static void main(String[] args) {
   	   SessionFactory sessionFactory;
	  sessionFactory = new Configuration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();

    // fetch="join" and fetch="select"
    Event e1 = (Event) session.get(Event.class,1L);
   Set list = (Set) e1.getSpeakers();
    System.out.println("#########");
    
    // fetch="select" and fetch="subselect"
  //  List list = session.createQuery("from Event").list();
  }
}
