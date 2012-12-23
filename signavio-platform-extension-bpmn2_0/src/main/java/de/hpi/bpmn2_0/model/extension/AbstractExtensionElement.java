package de.hpi.bpmn2_0.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;

import de.hpi.bpmn2_0.model.extension.signavio.SignavioLabel;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioMessageName;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioMetaData;
import de.hpi.bpmn2_0.model.extension.signavio.SignavioType;

/* EDE: */
import isabpmcenter.ppinot.jsontoxml.model.Ppiset;
/* fin EDE */

/**
 * Abstract BPMN 2.0 extension element
 * 
 * @author Sven Wagner-Boysen
 *
 */
@XmlSeeAlso({
	SignavioMetaData.class,
	SignavioType.class,
	SignavioLabel.class,
	SignavioMessageName.class,
	/* EDE: */
	Ppiset.class
	/* fin EDe */
})
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractExtensionElement {
	
}