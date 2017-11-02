
public class DynamicRouterTest {
/*
	int invoked=0;
	
	public String slip(String body) {

	    invoked++;

	    if (invoked == 1) {
	    
	        return "seda:a";
	    } else if (invoked == 2) {
	    
	        return "seda:b,seda:c";
	    } else if (invoked == 3) {
	        return "seda:d";
	    } else if (invoked == 4) {
	        return "seda:e";
	    }

	    // no more so return null
	    invoked=0;
	    return null;
	    }*/
	
	public String slip(String body)
	{
		System.out.println("inside : " + body);
		if(body.equals("sb"))
			return "direct:a";
		
		else if(body.equals("current"))
		    return "direct:b";
		
		return null;
		
		
		
	}
}
