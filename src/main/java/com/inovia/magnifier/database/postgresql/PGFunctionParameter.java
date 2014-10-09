package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.FunctionParameter;

public class PGFunctionParameter extends FunctionParameter {
	private String name;
	private String type;
	private String inOut;
	
	public PGFunctionParameter(String name, String type, String inOut) {
		this.name = name;
		this.type = type;
		this.inOut = inOut;
	}
}
