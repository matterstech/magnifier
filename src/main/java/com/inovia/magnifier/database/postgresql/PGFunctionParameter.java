package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.*;

/**
 * The class PGFunctionParameter deals with the PostgreSql
 * implementation of the FunctionParameter interface
 */
public class PGFunctionParameter implements FunctionParameter {
	private PGFunction function;
	private String name;
	private String mode;
	
	public PGFunctionParameter(PGFunction function, String name, String mode) {
		this.function = function;
		this.name = name;
		this.mode = mode;
	}

	public Function getFunction() {
		return function;
	}

	public String getEntityDescription() {
		String result = function.getSchemaName() + "." + function.getName() + "(";
		for(FunctionParameter fp : function.getParameters()) {
			result = result + (fp.getName() != null ? fp.getName() : "<noname>") + " " + fp.getMode();
		}
		result = result + ")";
		
		return result;
	}
	
	public String toString() {
		return name + " " + mode;
	}

	public String getName() {
		return name;
	}

	public String getMode() {
		return mode;
	}
}
