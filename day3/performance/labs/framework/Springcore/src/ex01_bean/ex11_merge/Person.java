package ex01_bean.ex11_merge;

import java.util.List;

public class Person {

	String first;
	String last;
	
	List Address;
	
	public List getAddress() {
		return Address;
	}
	public void setAddress(List address) {
		Address = address;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
}
