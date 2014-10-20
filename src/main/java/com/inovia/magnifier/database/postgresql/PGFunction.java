package com.inovia.magnifier.database.postgresql;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;

/**
 * The class PGFunction deals with the PostgreSql
 * implementation of the Function interface
 */
public class PGFunction implements Function {
	private String name;
	private String schemaName;
	private Vector<PGFunctionParameter> parameters;

	/**
	 * @param schemaName The name of the schema containing the function
	 * @param name       The name of the function
	 */
	public PGFunction(String schemaName, String name) {
		this.schemaName = schemaName;
		this.name = name;
		this.parameters = new Vector<PGFunctionParameter>();
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getName() {
		return name;
	}
	
	public Vector<FunctionParameter> getParameters() {
		Vector<FunctionParameter> res = new Vector<FunctionParameter>();
		for(PGFunctionParameter f : parameters) {
			res.add((FunctionParameter) f);
		}
		
		return res;
	}
	
	public void addParameter(FunctionParameter parameter) {
		parameters.add((PGFunctionParameter) parameter);
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
