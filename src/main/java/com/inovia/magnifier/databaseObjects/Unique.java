package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

/**
 * 
 * @author joeyrogues
 *
 */
public class Unique extends DatabaseObject {
	private String tableName;
	private String schema;
	private ArrayList<String> columnNames;
	
	/**
	 * 
	 * @param schema The schema that contains the unique constraint
	 * @param name The name of the unique constraint
	 * @param tableName The table on which the unique constraint applies 
	 * @param columnNames The column names on which the unique constraint applies
	 */
	public Unique(String schema, String name, String tableName, ArrayList<String> columnNames) {
		super(name);
		
		if(schema == null || schema.isEmpty()) {
			throw new IllegalArgumentException("a unique should have a schema");
		}
		
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("a unique should have a name");
		}
		
		if(tableName == null || tableName.isEmpty()) {
			throw new IllegalArgumentException("a unique should have an associated table");
		}
		
		if(columnNames == null || columnNames.isEmpty()) {
			throw new IllegalArgumentException("a unique should have columns");
		}
		
		this.schema = schema;
		this.tableName = tableName;
		this.columnNames = columnNames;
	}

	/**
	 * 
	 * @return The table on which the unique constraint applies 
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 
	 * @return The column names on which the unique constraint applies
	 */
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	/**
	 * 
	 * @return The schema that contains the unique constraint
	 */
	public String getSchema() {
		return schema;
	}
	
	public String toString() {
		return schema + "." + name + " [" + tableName + " " + columnNames + "]";
	}
}
