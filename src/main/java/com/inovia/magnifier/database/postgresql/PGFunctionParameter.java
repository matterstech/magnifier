package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.Function;
import com.inovia.magnifier.database.objects.FunctionParameter;

public class PGFunctionParameter implements FunctionParameter {
	private String name;
	private String type;
	private String inOut;
	
	public PGFunctionParameter(String name, String type, String inOut) {
		this.name = name;
		this.type = type;
		this.inOut = inOut;
	}

	public Function getFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEntityDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
