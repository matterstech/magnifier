package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

/**
 * 
 * @author joeyrogues
 *
 */
public class Unique extends DatabaseObject {
	private String tableName;
	private ArrayList<String> columnNames;
	
	/**
	 * 
	 * @param name The name of the unique constraint
	 * @param tableName The table on which the unique constraint applies 
	 * @param columnNames The column names on which the unique constraint applies
	 */
	public Unique(String name, String tableName, ArrayList<String> columnNames) {
		super(name);
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

	public String toString() {
		return name + " [" + tableName + " " + columnNames + "]";
	}
}
