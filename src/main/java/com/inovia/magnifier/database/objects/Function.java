package com.inovia.magnifier.database.objects;

import java.util.List;


public interface Function extends Entity {
	public String getSchemaName();
	public String getName();
	public List<FunctionParameter> getParameters();
	public void addParameter(FunctionParameter parameter);
	public String toString();
}
