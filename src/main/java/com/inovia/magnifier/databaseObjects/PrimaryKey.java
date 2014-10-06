package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

public class PrimaryKey extends DatabaseObject {
	private String tableName;
	private ArrayList<String> columnNames;
	
	/**
	 * 
	 * @param name The name of the primary key constraint
	 * @param tableName The name of the table containing the primary key
	 * @param columnName The name of the column on which the primary key constraint applies
	 */
	public PrimaryKey(String name, String tableName, ArrayList<String> columnNames) {
		super(name);
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
	
	public String toString() {
		return name + " [" + tableName + " " + columnNames + "]";
	}
}
