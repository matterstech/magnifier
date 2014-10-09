package com.inovia.magnifier.database.postgresql;

import java.util.*;

import com.inovia.magnifier.database.*;

public class PGSchema implements Schema {
	private String name;

	public PGSchema(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return "name: " + name;
	}
}
