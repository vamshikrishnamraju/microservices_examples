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
package org.jboss.tutorial.merge.client;

import org.jboss.tutorial.merge.bean.Customer;
import org.jboss.tutorial.merge.bean.CustomerDAO;

import javax.naming.InitialContext;

import java.util.List;


public class Client
{
   public static void main(String[] args) throws Exception
   {
      InitialContext ctx = new InitialContext();
      CustomerDAO dao = (CustomerDAO) ctx.lookup("CustomerDAOBean/remote");

Customer cust1 = (Customer) dao.find(2);
System.out.println("city : " + cust1.getCity());
//cust1.setCity("Delhi");

//dao.merge(cust1);

     //  System.out.println("Get all the Burkes");
    List burkes = dao.findByLastName("rushi");
      System.out.println("There are now " + burkes.size() + " Burkes");
      
      for(Object burk : burkes)
      {
    	Customer c = (Customer) burk;
    	System.out.println(" Name " + c.getState());
      }
   }
}
