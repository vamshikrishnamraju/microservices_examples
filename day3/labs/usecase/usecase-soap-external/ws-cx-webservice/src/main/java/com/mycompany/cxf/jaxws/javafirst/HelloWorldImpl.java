
package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebService;

@WebService(endpointInterface = "com.mycompany.cxf.jaxws.javafirst.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
    	System.out.println("######## called me... great god");
        return "Hello " + text;
    }
    
    public String sayHi2(String text) {
    	System.out.println("######## trust... great god");
        return "goodbye " + text;
    }
    
}
