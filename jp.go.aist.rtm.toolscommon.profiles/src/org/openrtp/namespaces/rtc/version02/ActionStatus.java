//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.11.22 at 05:14:48 �ߌ� JST 
//


package org.openrtp.namespaces.rtc.version02;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for action_status complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="action_status">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="implemented" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "action_status")
public class ActionStatus {

    @XmlAttribute(namespace = "http://www.openrtp.org/namespaces/rtc", required = true)
    protected boolean implemented;
    protected String implementeds;

//    /**
//     * Gets the value of the implemented property.
//     * 
//     */
//    public boolean isImplementeda() {
//        return implemented;
//    }

    /**
     * Sets the value of the implemented property.
     * 
     */
    public void setImplementedbln(boolean value) {
        this.implemented = value;
    }
    
    public String getImplemented() {
        if(implementeds==null) implementeds = Boolean.valueOf(implemented).toString();
        return implementeds;
    }
    public void setImplemented(String value) {
    	implementeds = value;
    }
}
