
package com.mycompany.cxf.jaxws.javafirst;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.cxf.jaxws.javafirst package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHi2_QNAME = new QName("http://javafirst.jaxws.cxf.mycompany.com/", "sayHi2");
    private final static QName _SayHi2Response_QNAME = new QName("http://javafirst.jaxws.cxf.mycompany.com/", "sayHi2Response");
    private final static QName _SayHi_QNAME = new QName("http://javafirst.jaxws.cxf.mycompany.com/", "sayHi");
    private final static QName _SayHiResponse_QNAME = new QName("http://javafirst.jaxws.cxf.mycompany.com/", "sayHiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.cxf.jaxws.javafirst
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link SayHi2 }
     * 
     */
    public SayHi2 createSayHi2() {
        return new SayHi2();
    }

    /**
     * Create an instance of {@link SayHi2Response }
     * 
     */
    public SayHi2Response createSayHi2Response() {
        return new SayHi2Response();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javafirst.jaxws.cxf.mycompany.com/", name = "sayHi2")
    public JAXBElement<SayHi2> createSayHi2(SayHi2 value) {
        return new JAXBElement<SayHi2>(_SayHi2_QNAME, SayHi2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javafirst.jaxws.cxf.mycompany.com/", name = "sayHi2Response")
    public JAXBElement<SayHi2Response> createSayHi2Response(SayHi2Response value) {
        return new JAXBElement<SayHi2Response>(_SayHi2Response_QNAME, SayHi2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javafirst.jaxws.cxf.mycompany.com/", name = "sayHi")
    public JAXBElement<SayHi> createSayHi(SayHi value) {
        return new JAXBElement<SayHi>(_SayHi_QNAME, SayHi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javafirst.jaxws.cxf.mycompany.com/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

}
