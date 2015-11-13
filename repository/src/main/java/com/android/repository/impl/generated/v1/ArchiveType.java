//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.11 at 05:53:38 PM PST 
//


package com.android.repository.impl.generated.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.android.repository.impl.meta.Archive;
import com.android.repository.impl.meta.TrimStringAdapter;


/**
 * 
 *                 A collection of files that can be downloaded for a given architecture.
 *                 The <archives> node is mandatory in the repository packages and the
 *                 collection must have at least one <archive> declared.
 *                 Each archive contains a <complete> element and zero or more
 *                 <patch> elements.
 *             
 * 
 * <p>Java class for archiveType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="archiveType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="host-os" type="{http://schemas.android.com/repository/android/common/01}osType" minOccurs="0"/&gt;
 *         &lt;element name="host-bits" type="{http://schemas.android.com/repository/android/common/01}bitSizeType" minOccurs="0"/&gt;
 *         &lt;element name="jvm-bits" type="{http://schemas.android.com/repository/android/common/01}bitSizeType" minOccurs="0"/&gt;
 *         &lt;element name="min-jvm-version" type="{http://schemas.android.com/repository/android/common/01}revisionType" minOccurs="0"/&gt;
 *         &lt;element name="complete" type="{http://schemas.android.com/repository/android/common/01}completeType"/&gt;
 *         &lt;element name="patches" type="{http://schemas.android.com/repository/android/common/01}patchesType" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "archiveType", propOrder = {

})
@SuppressWarnings({
    "override",
    "unchecked"
})
public class ArchiveType
    extends Archive
{

    @XmlElement(name = "host-os")
    @XmlJavaTypeAdapter(TrimStringAdapter.class)
    protected String hostOs;
    @XmlElement(name = "host-bits")
    protected Integer hostBits;
    @XmlElement(name = "jvm-bits")
    protected Integer jvmBits;
    @XmlElement(name = "min-jvm-version")
    protected com.android.repository.impl.generated.v1.RevisionType minJvmVersion;
    @XmlElement(required = true)
    protected com.android.repository.impl.generated.v1.CompleteType complete;
    protected com.android.repository.impl.generated.v1.PatchesType patches;

    /**
     * Gets the value of the hostOs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHostOs() {
        return hostOs;
    }

    /**
     * Sets the value of the hostOs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHostOs(String value) {
        this.hostOs = value;
    }

    /**
     * Gets the value of the hostBits property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHostBits() {
        return hostBits;
    }

    /**
     * Sets the value of the hostBits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHostBits(Integer value) {
        this.hostBits = value;
    }

    /**
     * Gets the value of the jvmBits property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getJvmBits() {
        return jvmBits;
    }

    /**
     * Sets the value of the jvmBits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setJvmBits(Integer value) {
        this.jvmBits = value;
    }

    /**
     * Gets the value of the minJvmVersion property.
     * 
     * @return
     *     possible object is
     *     {@link com.android.repository.impl.generated.v1.RevisionType }
     *     
     */
    public com.android.repository.impl.generated.v1.RevisionType getMinJvmVersion() {
        return minJvmVersion;
    }

    /**
     * Sets the value of the minJvmVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.android.repository.impl.generated.v1.RevisionType }
     *     
     */
    public void setMinJvmVersionInternal(com.android.repository.impl.generated.v1.RevisionType value) {
        this.minJvmVersion = value;
    }

    /**
     * Gets the value of the complete property.
     * 
     * @return
     *     possible object is
     *     {@link com.android.repository.impl.generated.v1.CompleteType }
     *     
     */
    public com.android.repository.impl.generated.v1.CompleteType getComplete() {
        return complete;
    }

    /**
     * Sets the value of the complete property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.android.repository.impl.generated.v1.CompleteType }
     *     
     */
    public void setCompleteInternal(com.android.repository.impl.generated.v1.CompleteType value) {
        this.complete = value;
    }

    /**
     * Gets the value of the patches property.
     * 
     * @return
     *     possible object is
     *     {@link com.android.repository.impl.generated.v1.PatchesType }
     *     
     */
    public com.android.repository.impl.generated.v1.PatchesType getPatches() {
        return patches;
    }

    /**
     * Sets the value of the patches property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.android.repository.impl.generated.v1.PatchesType }
     *     
     */
    public void setPatchesInternal(com.android.repository.impl.generated.v1.PatchesType value) {
        this.patches = value;
    }

    public void setMinJvmVersion(com.android.repository.impl.meta.RevisionType value) {
        setMinJvmVersionInternal(((com.android.repository.impl.generated.v1.RevisionType) value));
    }

    public void setComplete(Archive.CompleteType value) {
        setCompleteInternal(((com.android.repository.impl.generated.v1.CompleteType) value));
    }

    public void setPatches(Archive.PatchesType value) {
        setPatchesInternal(((com.android.repository.impl.generated.v1.PatchesType) value));
    }

    public ObjectFactory createFactory() {
        return new ObjectFactory();
    }

}
