package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

/**
 * 
 * @author joeyrogues
 *
 */
public class Table extends DatabaseObject {
	private ArrayList<String> columns;
	/**
	 * 
	 * @param name    The name of the table
	 * @param columns The list of columns the table contains
	 */
	public Table(String name, ArrayList<String> columns) {
		super(name);
		
		this.columns = columns;
	}
	
	/**
	 * 
	 * @return The list of columns for the table
	 */
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public String toString() {
		return name + " " + columns;
	}
}
