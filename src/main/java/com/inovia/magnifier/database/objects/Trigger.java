package com.inovia.magnifier.database.objects;

public interface Trigger extends Entity {
	public String getSchemaName();
	public String getTableName();
	public String getName();
	public String getAction();
	public String getTiming();
}
