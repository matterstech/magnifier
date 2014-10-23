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
		
		assertEquals("public", t.getSchemaName());
		assertEquals("table1", t.getName());
	}
	
	public void testPrimaryKeys() {
		Table t = new Table("public", "table1");
		
		assertEquals(0, t.getPrimaryKey().size());
		t.addPrimaryKey("field1");
		assertEquals("field1", t.getPrimaryKey().get(0));
	}
	
	public void testForeignKeys() {
		Table t = new Table("public", "table1");
		
		assertEquals(0, t.getForeignKeys().size());
		
		ForeignKey fk = new ForeignKey(t, "table2_field2", "public", "table2", "field2");
		t.addForeignKey(fk);
		
		assertEquals(1, t.getForeignKeys().size());
		assertEquals(fk, t.getForeignKeys().get(0));
	}
}
