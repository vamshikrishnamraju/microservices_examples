package  ex01_bean.ex09_property_EL;

import java.util.List;


import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;


public class Bean2 implements BeanNameAware
{
	
		
	int no;
	
	String name;
	
	List hobbies;
	
	public List getHobbies() {
		return hobbies;
	}
	public void setHobbies(List hobbies) {
		this.hobbies = hobbies;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}
	
	public void init()
	{
		System.out.println("Init##### " + no + "  "+ name);
	}
	public void setBeanName(String arg0) {
		System.out.println("SetBeanname##### " + no + "  "+ name);
		
	}
   
}
