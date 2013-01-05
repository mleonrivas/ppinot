package es.us.isa.bpmn.owl.converter;

import java.util.Iterator;
import java.util.List;
//import org.apache.bcel.generic.NEW;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import es.us.isa.bpmn.owl.notation.Vocabulary;

/**
 * @author Ana Belen Sanchez Jerez
 * 
 * Clase donde se van a definir las funciones que convierten los datos pasados por parametros
 * en instancias en owl 
**/
public class GenerateBpmnAxioms {

	private OWLDataFactory factory;		// Factory utilizada para generar los elementos owl
	private OWLOntologyManager manager;	// OWLOntologyManager utilizado
	private OWLOntology ontology;		// Ontologia a la que se adicionan los axiomas
	
	private String generatedOntologyURI; 		// URI de la ontologia generada
	
	/**Constructor de GenerateOWL **/
	public GenerateBpmnAxioms(OWLDataFactory factory, OWLOntologyManager manager, OWLOntology ontology, 
			String generatedOntologyURI){

		this.factory = factory;
		this.manager = manager;
		this.ontology = ontology;

		this.generatedOntologyURI = generatedOntologyURI;
	}
	
	/**Funcion para convertir elementos de tipo Activity a individuals en codigo owl
	 * @param nameDataObj 
	 * @param elementsDirectlyPrecedes **/
	public void converterActivityOWL(String nameActivity, String nameDataObj, List<String> elementsDirectlyPrecedes){
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameActivity);
 		IRI iri3 = IRI.create(generatedOntologyURI+"#"+nameDataObj);
		
        OWLNamedIndividual taskNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass activityClass = factory.getOWLClass(IRI.create(Vocabulary.ACTIVITY_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(activityClass, taskNameIndividual);
        
        if(nameDataObj != null){
        	OWLObjectPropertyExpression output = factory.getOWLObjectProperty(IRI.create(Vocabulary.DATAOUTPUTASSOCIATION_URI));
        	OWLNamedIndividual dataObjNameIndividualMeasure = factory.getOWLNamedIndividual(iri3);
        	OWLObjectPropertyAssertionAxiom propertyAssertion = factory.getOWLObjectPropertyAssertionAxiom(output, taskNameIndividual, dataObjNameIndividualMeasure);
        	manager.addAxiom(ontology, propertyAssertion);
        }
        
        if(elementsDirectlyPrecedes.size() > 0){
        	
        	Iterator<String> it = elementsDirectlyPrecedes.iterator();
        	while(it.hasNext()){
        		String elementDirectly = it.next();
        		OWLObjectPropertyExpression directlyPrecedes = factory.getOWLObjectProperty(IRI.create(Vocabulary.DIRECTLYPRECEDES_URI));
        		OWLNamedIndividual DataObjNameElementdirectly = factory.getOWLNamedIndividual(IRI.create(generatedOntologyURI+"#"+elementDirectly));
        		OWLObjectPropertyAssertionAxiom propertyAssertion2 = factory.getOWLObjectPropertyAssertionAxiom(directlyPrecedes, taskNameIndividual, DataObjNameElementdirectly);
        		manager.addAxiom(ontology, propertyAssertion2);
        	}
        }
        manager.addAxiom(ontology, classAssertion);
        
	}

	/**Funcion para convertir elementos de tipo StartEvent a individuals en codigo owl
	 * @param elementsDirectlyPrecedes **/
	public void converterStartEventOWL(String nameEvent, List<String> elementsDirectlyPrecedes) {
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameEvent);
 		
        OWLNamedIndividual EventNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass StartEvent = factory.getOWLClass(IRI.create(Vocabulary.STARTEVENT_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(StartEvent, EventNameIndividual);
       
        if(elementsDirectlyPrecedes.size() > 0){
        	
        	Iterator<String> it = elementsDirectlyPrecedes.iterator();
        	while(it.hasNext()){
        		String elementDirectly = it.next();
        		OWLObjectPropertyExpression directlyPrecedes = factory.getOWLObjectProperty(IRI.create(Vocabulary.DIRECTLYPRECEDES_URI));
        		OWLNamedIndividual DataObjNameElementdirectly = factory.getOWLNamedIndividual(IRI.create(generatedOntologyURI+"#"+elementDirectly));
        		OWLObjectPropertyAssertionAxiom propertyAssertion2 = factory.getOWLObjectPropertyAssertionAxiom(directlyPrecedes, EventNameIndividual, DataObjNameElementdirectly);
        		manager.addAxiom(ontology, propertyAssertion2);
        	}
        }
        manager.addAxiom(ontology, classAssertion);
	}
	
	/**Funcion para convertir elementos de tipo EndEvent a individuals en codigo owl**/
	public void converterEndEventOWL(String nameEvent) {
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameEvent);
 		
        OWLNamedIndividual EventNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass Event = factory.getOWLClass(IRI.create(Vocabulary.ENDEVENT_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(Event, EventNameIndividual);
        manager.addAxiom(ontology, classAssertion);	
	}

	/**Funcion para convertir elementos de tipo XorGateway a individuals en codigo owl
	 * @param elementsDirectlyPrecedes **/
	public void converterXorGatewayOWL(String nameGtw, List<String> elementsDirectlyPrecedes) {
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameGtw);
 		
        OWLNamedIndividual GtwNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass Gtw = factory.getOWLClass(IRI.create(Vocabulary.XORGATEWAY_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(Gtw, GtwNameIndividual);
        
        if(elementsDirectlyPrecedes.size() > 0){
        	Iterator<String> it = elementsDirectlyPrecedes.iterator();
        	while(it.hasNext()){
        		String elementDirectly = it.next();
        		OWLObjectPropertyExpression directlyPrecedes = factory.getOWLObjectProperty(IRI.create(Vocabulary.DIRECTLYPRECEDES_URI));
        		OWLNamedIndividual DataObjNameElementdirectly = factory.getOWLNamedIndividual(IRI.create(generatedOntologyURI+"#"+elementDirectly));
        		OWLObjectPropertyAssertionAxiom propertyAssertion2 = factory.getOWLObjectPropertyAssertionAxiom(directlyPrecedes, GtwNameIndividual, DataObjNameElementdirectly);
        		manager.addAxiom(ontology, propertyAssertion2);
        	}
        }
        manager.addAxiom(ontology, classAssertion);	
	}

	/**Funcion para convertir elementos de tipo Gateway a individuals en codigo owl
	 * @param elementsDirectlyPrecedes **/
	public void converterGatewayOWL(String nameGtw, List<String> elementsDirectlyPrecedes) {
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameGtw);
 		
        OWLNamedIndividual GtwNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass Gtw = factory.getOWLClass(IRI.create(Vocabulary.GATEWAY_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(Gtw, GtwNameIndividual);
        if(elementsDirectlyPrecedes.size() > 0){
        	Iterator<String> it = elementsDirectlyPrecedes.iterator();
        	while(it.hasNext()){
        		String elementDirectly = it.next();
        		OWLObjectPropertyExpression directlyPrecedes = factory.getOWLObjectProperty(IRI.create(Vocabulary.DIRECTLYPRECEDES_URI));
        		OWLNamedIndividual DataObjNameElementdirectly = factory.getOWLNamedIndividual(IRI.create(generatedOntologyURI+"#"+elementDirectly));
        		OWLObjectPropertyAssertionAxiom propertyAssertion2 = factory.getOWLObjectPropertyAssertionAxiom(directlyPrecedes, GtwNameIndividual, DataObjNameElementdirectly);
        		manager.addAxiom(ontology, propertyAssertion2);
        	}
        }
        manager.addAxiom(ontology, classAssertion);	
	}

	/**Funcion para convertir elementos de tipo DataObject a individuals en codigo owl**/
	public void converterDataObjectOWL(String nameDataObj) {
		
		IRI iri2 = IRI.create(generatedOntologyURI+"#"+nameDataObj);
 		
        OWLNamedIndividual DataObjNameIndividual = factory.getOWLNamedIndividual(iri2);
        OWLClass DataObj = factory.getOWLClass(IRI.create(Vocabulary.DATAOBJECT_URI));
        OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(DataObj, DataObjNameIndividual);
        manager.addAxiom(ontology, classAssertion);	
	}

	
}
