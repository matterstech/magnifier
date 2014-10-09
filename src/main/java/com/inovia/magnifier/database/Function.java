package com.inovia.magnifier.database;

import java.util.Vector;


public abstract class Function {
	protected static Vector<Function> pool;
	
	public static Vector<Function> getAll() {
		throw new UnsupportedOperationException("not yet implemented");
	}

	public static void load() { 
		throw new UnsupportedOperationException("not yet implemented");
	}
}