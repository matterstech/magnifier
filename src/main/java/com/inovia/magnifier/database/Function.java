package com.inovia.magnifier.database;

import java.util.*;

/**
 * The class PGFunction deals with the PostgreSql
 * implementation of the Function interface
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

	public String toString() {
		return "schema: "+ schemaName + ", name: "+ name;
	}

	public String getEntityDescription() {
		
		String result = schemaName + "." + name + "(";
		for(FunctionParameter fp : parameters) {
			result = result + (fp.getName() != null ? fp.getName() : "<noname>") + " " + fp.getMode();
		}
		result = result + ")";
		
		return result;
	}
}
