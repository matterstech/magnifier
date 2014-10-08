package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

import junit.framework.*;

public class PrimaryKeyTest extends TestCase {
	public PrimaryKeyTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PrimaryKeyTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		ArrayList<String> columnsNames = new ArrayList<String>();
		columnsNames.add("table1_id");
		
		PrimaryKey pk = new PrimaryKey("public", "table1_pk", "table1", columnsNames);
		
		assertEquals(pk.getSchemaName(), "public");
		assertEquals(pk.getName(), "table1_pk");
		assertEquals(pk.getTableName(), "table1");
		assertEquals(pk.getColumnName(), columnsNames);
	}
	
	public void testConstructorWithWrongParameters() {
		ArrayList<String> columnsNames = new ArrayList<String>();
		columnsNames.add("table1_id");
		
		try {
			PrimaryKey pk = new PrimaryKey(null, "table1_pk", "table1", columnsNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a primary key should have a schema");
		}
		
		try {
			PrimaryKey pk = new PrimaryKey("public", null, "table1", columnsNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a primary key should have a name");
		}
		
		try {
			PrimaryKey pk = new PrimaryKey("public", "table1_pk", null, columnsNames);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a primary key should have a table associated");
		}
		
		try {
			PrimaryKey pk = new PrimaryKey("public", "table1_pk", "table1", null);
			fail("should have thrown exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a primary key should have columns");
		}
	}
}
