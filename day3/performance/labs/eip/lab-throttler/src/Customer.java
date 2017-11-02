import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Body;


public class Customer {

	
public List info(@Body String s)
	{
		
	
	List message = new ArrayList();
	
	message.add(s);
	message.add("great");
	return message;
		
	}
}
