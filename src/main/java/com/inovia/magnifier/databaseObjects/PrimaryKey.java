package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

public class PrimaryKey extends DatabaseObject {
	private String tableName;
	private String schemaName;
	private ArrayList<String> columnNames;
	
	/**
	 * 
	 * @param name The name of the primary key constraint
	 * @param tableName The name of the table containing the primary key
	 * @param columnName The name of the column on which the primary key constraint applies
	 */
	public PrimaryKey(String schemaName, String name, String tableName, ArrayList<String> columnNames) {
		super(name);
		
		if(schemaName == null || schemaName.isEmpty()) {
			throw new IllegalArgumentException("a primary key should have a schema");
		}
		
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("a primary key should have a name");
		}
		
		if(tableName == null || tableName.isEmpty()) {
			throw new IllegalArgumentException("a primary key should have a table associated");
		}
		
		if(columnNames == null || columnNames.isEmpty()) {
			throw new IllegalArgumentException("a primary key should have columns");
		}
		
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.columnNames = columnNames;
	}

	/**
	 * 
	 * @return The name of the table containing the primary key
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 
	 * @return The list of columns which compose the primary key
	 */
	public ArrayList<String> getColumnName() {
		return columnNames;
	}
	
	/**
	 * 
	 * @return The schema that contains the primary key
	 */
	public String getSchemaName() {
		return schemaName;
	}
	
	public String toString() {
		return schemaName + "." + name + " [" + tableName + " " + columnNames + "]";
	}
}
