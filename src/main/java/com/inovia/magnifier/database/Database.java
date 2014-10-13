package com.inovia.magnifier.database;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;


public interface Database {
	public void connect();
	public void disconnect();
	public void load();
	
	public String getName();
	
	public Vector<Schema> getSchemas();
	public Vector<Function> getFunctions();
	public Vector<Comment> getComments();
	public Vector<Table> getTables();
	public Vector<Index> getIndexes();
}
