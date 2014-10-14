package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

import junit.framework.*;

public class UniqueTest extends TestCase {
	public UniqueTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(UniqueTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("table1_id");
		
		Unique u = new Unique("public", "table1_pk", "table1", columnNames);
		
		assertEquals(u.getSchema(), "public");
		assertEquals(u.getName(), "table1_pk");
		assertEquals(u.getTableName(), "table1");
		assertEquals(u.getColumnNames(), columnNames);
	}
	
	public void testConstructorWithWrongParameters() {
		ArrayList<String> columnNames = new ArrayList<String>();
		columnNames.add("table1_id");
		
		try {
			Unique u = new Unique(null, "table1_pk", "table1", columnNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a unique should have a schema");
		}

		try {
			Unique u = new Unique("public", null, "table1", columnNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a unique should have a name");
		}
		
		try {
			Unique u = new Unique("public", "table1_pk", null, columnNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a unique should have an associated table");
		}
		

		try {
			Unique u = new Unique("public", "table1_pk", "table1", null);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a unique should have columns");
		}
	}
}
