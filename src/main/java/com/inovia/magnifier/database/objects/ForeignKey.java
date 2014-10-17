package com.inovia.magnifier.database.objects;

public interface ForeignKey extends Entity {
	public String getSchemaName();
	public Table getTable();
	public String getColumnName();
	
	public String getForeignSchemaName();
	public String getForeignTableName();
	public String getForeignColumnName();
	
	public String toString();
}
