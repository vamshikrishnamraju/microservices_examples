package com.fusesource.training;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.0.redhat-60024
 * 2016-12-25T20:06:41.744+05:30
 * Generated source version: 2.6.0.redhat-60024
 * 
 */
@WebServiceClient(name = "CustomerService", 
                  wsdlLocation = "file:/D:/fuse/labs/camel-exercises/webservice/src/main/resources/META-INF/wsdl/CustomerService.wsdl",
                  targetNamespace = "http://training.fusesource.com/") 
public class CustomerService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://training.fusesource.com/", "CustomerService");
    public final static QName CustomerServicePort = new QName("http://training.fusesource.com/", "CustomerServicePort");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/fuse/labs/camel-exercises/webservice/src/main/resources/META-INF/wsdl/CustomerService.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CustomerService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/fuse/labs/camel-exercises/webservice/src/main/resources/META-INF/wsdl/CustomerService.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CustomerService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CustomerService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CustomerService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CustomerService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CustomerService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public CustomerService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerServicePort")
    public CustomerService getCustomerServicePort() {
        return super.getPort(CustomerServicePort, CustomerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CustomerService
     */
    @WebEndpoint(name = "CustomerServicePort")
    public CustomerService getCustomerServicePort(WebServiceFeature... features) {
        return super.getPort(CustomerServicePort, CustomerService.class, features);
    }

}