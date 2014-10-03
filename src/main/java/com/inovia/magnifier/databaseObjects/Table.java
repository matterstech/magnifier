package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;


public class Table extends DatabaseObject {
	private ArrayList<String> columns;
	
	public Table(String name, ArrayList<String> columns) {
		super(name);
		
		this.columns = columns;
	}
	
	public ArrayList<String> getColumns() {
		return columns;
	}
	
	public String toString() {
		return name + " " + columns;
	}
}
