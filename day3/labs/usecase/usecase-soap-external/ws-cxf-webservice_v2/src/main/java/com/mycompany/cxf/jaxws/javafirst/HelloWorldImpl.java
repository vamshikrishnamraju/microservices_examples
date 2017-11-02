
package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.mycompany.cxf.jaxws.javafirst.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	@WebMethod(operationName="sayHi", 
		    action="http://sample.com/Hello/sayHi") 
    public String sayHi(String text) {
    	System.out.println("######## called me... great god");
        return "Hello " + text;
    }
    
	
	@WebMethod(operationName="sayHi2", 
		    action="http://sample.com/Hello/sayHi2") 
    public String sayHi2(String text) {
    	System.out.println("######## trust... great god");
        return "goodbye " + text;
    }
    
}
