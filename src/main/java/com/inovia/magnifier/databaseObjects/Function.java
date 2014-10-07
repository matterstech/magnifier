package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

public class Function extends DatabaseObject {
	private ArrayList<FunctionParameter> parameters;
	/**
	 * 
	 * @param name The name of the function
	 * @param parameters The list of parameters for the function
	 */
	public Function(String name, ArrayList<FunctionParameter> parameters) {
		super(name);
		
		if(parameters == null) {
			throw new IllegalArgumentException("a function should have parameters list, even if empty");
		}
		
		this.parameters = parameters;
	}
	
	/**
	 * 
	 * @return The list of the function parameters
	 */
	public ArrayList<FunctionParameter> getParameters() {
		return parameters;
	}
	
	public String toString() {
		return name + (parameters.isEmpty() ? "<noparameters>" : parameters);
	}
}
