/*
* JBoss, Home of Professional Open Source
* Copyright 2005, JBoss Inc., and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/
package org.jboss.tutorial.merge.bean;

import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.CacheMode;
import org.hibernate.Session;

@Stateless
@Remote(CustomerDAO.class)
public class CustomerDAOBean implements CustomerDAO
{
   @PersistenceContext
   private Session manager;


   public int create(String first, String last, String street, String city, String state, String zip)
   {
      Customer customer = new Customer(first, last, street, city, state, zip);
      manager.save(customer);
      return customer.getId();
   }

   public Customer find(int id)
   {
	//manager.setCacheMode(CacheMode.IGNORE);
	   Customer cust = (Customer)  manager.get(Customer.class, id);
         return cust;
   }

   public List findByLastName(String name)
   {
	//   List test = manager.createQuery("select c from Message c").getResultList();
	//   return manager.createQuery("select c from Customer c where c.last = :name").setParameter("name", name).getResultList();
  
	 //  List list = (List) manager.createQuery("from Customer").setCacheable(true).list();
     return manager.createQuery("select c from Customer c where c.last = :name").setParameter("name", name).setCacheable(true).list();
   }

   public void merge(Customer c)
   {
	// manager.setCacheMode(CacheMode.PUT);
      manager.merge(c);
   }
}
