package exA;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class BeanA {

	//static String test="";
	
	public void disp(Exchange msg)
	{
		
		Message m = msg.getIn();
		
	String	test = (String) m.getBody();
		
		System.out.println("inside disp1 bean :" + test);
	}
	
	public void disp2(Exchange msg)
	{
		
		Message m = msg.getIn();
		
	String	test = (String) m.getBody();
		
		System.out.println("inside disp2 bean :" + test);
	}
	
}
 