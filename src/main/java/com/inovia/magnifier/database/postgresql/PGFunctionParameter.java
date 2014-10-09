package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.*;

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
		return "PARAMETER " + name + " " + mode;
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
