package es.us.isa.ppinot.model.base;

import es.us.isa.ppinot.model.condition.DataPropertyCondition;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * Clase con la informacion de un PPI del tipo DataPropertyConditionInstanceMeasure
 * 
 * @author Edelia Garcia Gonzalez
 * @version 1.0
 *
 */
public class DataPropertyConditionInstanceMeasure extends ConditionMeasure {
	
	/**
	 * Constructor de la clase
	 */
	public DataPropertyConditionInstanceMeasure() {
		super();
	}
	
	/**
	 * Constructor de la clase
	 * 
     * @param id Id de la medida
     * @param name Nombre de la medida
     * @param description Descripcio de la medida
     * @param scale Escala de la medida
     * @param unitOfMeasure Unidad de medida
	 * @param condition Condicion de la medida
	 */
	public DataPropertyConditionInstanceMeasure(String id, String name, String description, String scale, String unitOfMeasure,
			DataPropertyCondition condition) {
		super(id, name, description, scale, unitOfMeasure, condition);
	}

	/**
     * Devuelve el atributo condition:
     * Condicion de la medida
     * 
     * @return Valor del atributo
     */
	public DataPropertyCondition getCondition() {
		return (DataPropertyCondition) super.getCondition();
	}

    /**
     * Da valor al atributo condition:
     * Condicion de la medida
     * 
     * @param value Valor del atributo
     */
	public void setCondition(DataPropertyCondition condition) {
		super.setCondition(condition);
	}
	
	/**
	 * Indica si el valor la medida puede ser calculada
	 * 
	 * @return 
	 */
	public Boolean getCond() {
		
		return super.getCond() &&
				this.getCondition().getDataobject()!=null && this.getCondition().getDataobject()!="" &&	
				this.getCondition().getStateConsidered().getStateString()!=null && this.getCondition().getStateConsidered().getStateString()!="";
	}

}
