package com.inovia.magnifier.database;

import junit.framework.*;


public class TableTest extends TestCase {
	public TableTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TableTest.class);
    }
	
	public void testConstructorWithRightParamters() {
		Table t = new Table("public", "table1");
		
		assertEquals(t.getSchemaName(), "public");
		assertEquals(t.getName(), "table1");
	}
	
	public void testPrimaryKeys() {
		Table t = new Table("public", "table1");
		
		assertEquals(t.getPrimaryKey().size(), 0);
		t.addPrimaryKey("field1");
		assertEquals(t.getPrimaryKey().get(0), "field1");
	}
	
	public void testForeignKeys() {
		Table t = new Table("public", "table1");
		
		assertEquals(t.getForeignKeys().size(), 0);
		
		ForeignKey fk = new ForeignKey(t, "table2_field2", "public", "table2", "field2");
		t.addForeignKey(fk);
		
		assertEquals(t.getForeignKeys().size(), 1);
		assertEquals(t.getForeignKeys().get(0), fk);
	}
}
