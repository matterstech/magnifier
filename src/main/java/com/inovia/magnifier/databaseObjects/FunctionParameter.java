package com.inovia.magnifier.databaseObjects;

/**
 * 
 * @author joeyrogues
 *
 */
public class FunctionParameter extends DatabaseObject {
	private String type;
	private Boolean isIn;
	
	/**
	 * 
	 * @param name The name of the parameter
	 * @param type The type of the parameter (char, int, ...)
	 * @param isIn A boolean value, true if parameter is an input (IN), false if it is an output (OUT)
	 */
	public FunctionParameter(String name, String type, Boolean isIn) {
		super(name);
		
		if(type == null || type.isEmpty()) {
			throw new IllegalArgumentException("a function parameter should have a type");
		}
		
		if(isIn == null) {
			throw new IllegalArgumentException("a function parameter should be INPUT or OUTPUT");
		}
		
		this.type = type;
		this.isIn = isIn;
	}
	
	public String toString() {
		return ( name == null || name.isEmpty() ? "<noname>" : name ) + " of " + type + " " + ( isIn() ? "IN" : "OUT" );
	}
	
	/**
	 * 
	 * @return true when parameter is an input (IN)
	 */
	public Boolean isIn() {
		return isIn;
	}
	
	/**
	 * 
	 * @return true when parameter is an output (OUT)
	 */
	public Boolean isOut() {
		return !isIn;
	}
	
	public String getType() {
		return type;
	}
}
