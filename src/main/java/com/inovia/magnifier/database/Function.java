package com.inovia.magnifier.database;

import java.util.*;

/**
 * it represents a database function
 */
public class Function {
	private String name;
	private String schemaName;
	private List<FunctionParameter> parameters;

	/**
	 * @param schemaName The name of the schema containing the function
	 * @param name       The name of the function
	 */
	public Function(String schemaName, String name) {
		this.schemaName = schemaName;
		this.name = name;
		this.parameters = new Vector<FunctionParameter>();
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getName() {
		return name;
	}
	
	public List<FunctionParameter> getParameters() {
		return parameters;
	}
	
	public void addParameter(FunctionParameter parameter) {
		parameters.add(parameter);
	}
	
	public String getDetails() {
		String html = ""
				+ "<div class=\"function-description\">";
		if(getParameters().size() == 0) {
			html = html
					+ getName() + "( )";
		} else {
			html = html
					+ getName() + "(";
			
			for(Integer i = 0 ; i < getParameters().size()-1 ; i++) {
				FunctionParameter p = getParameters().get(i);
				
				html = html
						+ p.getName() + " " + p.getMode() + ", ";
			}
			FunctionParameter lastParameter = getParameters().get(getParameters().size()-1);
			html = html
					+ lastParameter.getName() + " " + lastParameter.getMode()
					+ ")";
		}
		html = html
				+ "</div>";
		
		return html;
	}
}
