
package com.fabiorapanelo.order;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.fabiorapanelo.order package. 
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

    private final static QName _CheckStatusResponse_QNAME = new QName("http://order.fabiorapanelo.com/", "checkStatusResponse");
    private final static QName _CheckStatus_QNAME = new QName("http://order.fabiorapanelo.com/", "checkStatus");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.fabiorapanelo.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckStatus }
     * 
     */
    public CheckStatus createCheckStatus() {
        return new CheckStatus();
    }

    /**
     * Create an instance of {@link CheckStatusResponse }
     * 
     */
    public CheckStatusResponse createCheckStatusResponse() {
        return new CheckStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.fabiorapanelo.com/", name = "checkStatusResponse")
    public JAXBElement<CheckStatusResponse> createCheckStatusResponse(CheckStatusResponse value) {
        return new JAXBElement<CheckStatusResponse>(_CheckStatusResponse_QNAME, CheckStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://order.fabiorapanelo.com/", name = "checkStatus")
    public JAXBElement<CheckStatus> createCheckStatus(CheckStatus value) {
        return new JAXBElement<CheckStatus>(_CheckStatus_QNAME, CheckStatus.class, null, value);
    }

}
