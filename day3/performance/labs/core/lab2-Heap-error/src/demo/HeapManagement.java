package demo;

import java.util.ArrayList;
import java.util.List;

public class HeapManagement implements HeapManagementMBean{

	 List<Account>  acc = new ArrayList<Account>();
	 
	 float amt;
	 
	 
	 
	 public float getAmt() {
		return amt;
	}


	public void setAmt(float amt) {
		this.amt = amt;
	}


	public  void  display()
	   {

		 System.out.println("frst value: " + acc.size());
		 
		 System.out.println("processors : " + Runtime.getRuntime().availableProcessors());
		 
		 for (int count=0;count <50000;count++){
	    	   Account account = new Account();
		       account.setName("company");
		       account.setType("current");
	    	   account.setId(count);
	         acc.add(account);
	       //  System.out.println("value: " + acc.size());
	       }
	   }
	 
	 
	 public void clear()
	 {
		
	//	acc.clear();
		 acc=null;
	//	 System.out.println("size is: "+ acc.size());
	 }
	 
}
