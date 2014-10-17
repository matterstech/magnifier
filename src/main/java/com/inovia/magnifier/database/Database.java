package com.inovia.magnifier.database;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;

/**
 * The Database interface deals with the expected
 * behaviour of any kind of relational databases
 */
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
	public Vector<Trigger> getTriggers();
	public Vector<View> getViews();
}
