package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorld {
	
	@WebMethod(operationName="sayHi", 
		    action="http://sample.com/Hello/sayHi") 
    String sayHi(String text);
	
	@WebMethod(operationName="sayHi2", 
		    action="http://sample.com/Hello/sayHi2") 
    String sayHi2(String text);
}
