package com.inovia.magnifier.database;

import java.util.Vector;


public interface Database {
	public void connect();
	public void disconnect();
	public void load();
	
	public Vector<? extends Schema> getSchemas();
	public Vector<? extends Function> getFunctions();
}
