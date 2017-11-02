package com.mycompany.cxf.jaxws.javafirst;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.6.0.redhat-60024
 * 2016-12-26T11:27:07.753+05:30
 * Generated source version: 2.6.0.redhat-60024
 * 
 */
@WebService(targetNamespace = "http://javafirst.jaxws.cxf.mycompany.com/", name = "HelloWorld")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloWorld {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHi2", targetNamespace = "http://javafirst.jaxws.cxf.mycompany.com/", className = "com.mycompany.cxf.jaxws.javafirst.SayHi2")
    @WebMethod
    @ResponseWrapper(localName = "sayHi2Response", targetNamespace = "http://javafirst.jaxws.cxf.mycompany.com/", className = "com.mycompany.cxf.jaxws.javafirst.SayHi2Response")
    public java.lang.String sayHi2(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHi", targetNamespace = "http://javafirst.jaxws.cxf.mycompany.com/", className = "com.mycompany.cxf.jaxws.javafirst.SayHi")
    @WebMethod
    @ResponseWrapper(localName = "sayHiResponse", targetNamespace = "http://javafirst.jaxws.cxf.mycompany.com/", className = "com.mycompany.cxf.jaxws.javafirst.SayHiResponse")
    public java.lang.String sayHi(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}