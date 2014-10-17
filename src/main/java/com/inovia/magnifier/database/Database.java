package com.inovia.magnifier.database;

import java.util.List;

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
	
	public List<Schema> getSchemas();
	public List<Function> getFunctions();
	public List<Comment> getComments();
	public List<Table> getTables();
	public List<Index> getIndexes();
	public List<Trigger> getTriggers();
	public List<View> getViews();
}
