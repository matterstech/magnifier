package com.inovia.magnifier.database.objects;

import java.util.Vector;


public interface Function extends Entity {
	public String getSchemaName();
	public String getName();
	public Vector<FunctionParameter> getParameters();
	public void addParameter(FunctionParameter parameter);
	public String toString();
}
