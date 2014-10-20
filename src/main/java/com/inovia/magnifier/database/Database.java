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
	
	/**
	 * @return the database name
	 */
	public String getName();
	
	/**
	 * @return the list of schemas found in the database
	 */
	public List<Schema> getSchemas();
	
	/**
	 * @return the list of functions found in the database
	 */
	public List<Function> getFunctions();
	
	/**
	 * @return the list of comments found in the database
	 */
	public List<Comment> getComments();
	
	/**
	 * @return the list of tables found in the database
	 */
	public List<Table> getTables();
	
	/**
	 * @return the list of indexes found in the database
	 */
	public List<Index> getIndexes();
	
	/**
	 * @return the list of triggers found in the database
	 */
	public List<Trigger> getTriggers();
	
	/**
	 * @return the list of views found in the database
	 */
	public List<View> getViews();
}
