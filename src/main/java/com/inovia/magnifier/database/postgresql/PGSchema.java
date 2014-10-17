package com.inovia.magnifier.database.postgresql;

import java.util.Vector;

import com.inovia.magnifier.database.objects.Function;
import com.inovia.magnifier.database.objects.Schema;

/**
 * The class PGSchema deals with the PostgreSql
 * implementation of the Schema interface
 */
public class PGSchema implements Schema {
	private String name;
	private Vector<PGFunction> functions;

	public PGSchema(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "name: " + name;
	}

	public Vector<Function> getFunctions() {
		Vector<Function> res = new Vector<Function>();
		
		for(PGFunction f : functions) {
			res.add((Function) f); 
		}
		
		return res;
	}

	public String getEntityDescription() {
		return "SCHEMA " + name;
	}
}
