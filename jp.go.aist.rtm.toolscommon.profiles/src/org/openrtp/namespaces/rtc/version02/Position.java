//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.3 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.04.01 at 11:46:04 午前 JST 
//


package org.openrtp.namespaces.rtc.version02;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for position.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="position">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LEFT"/>
 *     &lt;enumeration value="RIGHT"/>
 *     &lt;enumeration value="TOP"/>
 *     &lt;enumeration value="BOTTOM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum Position {

    LEFT,
    RIGHT,
    TOP,
    BOTTOM;

    public String value() {
        return name();
    }

    public static Position fromValue(String v) {
        return valueOf(v);
    }

}
