package ex01_bean.ex02_setter;

import java.util.List;


public class Bean1  
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

	
   
}
