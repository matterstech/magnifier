package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;


public class Table extends DatabaseObject {
	private ArrayList<String> columns;
	
	public Table(String name) {
		super(name);
		
		columns = new ArrayList<String>();
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public void addColumn(String columnName) {
		columns.add(columnName);
	}
	
	public String toString() {
		return name + " " + columns;
	}
}
