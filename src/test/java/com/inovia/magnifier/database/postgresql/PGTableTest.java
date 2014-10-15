package com.inovia.magnifier.database.postgresql;

import junit.framework.*;


public class PGTableTest extends TestCase {
	public PGTableTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PGTableTest.class);
    }
	
	public void testConstructorWithRightParamters() {
		PGTable t = new PGTable("public", "table1");
		
		assertEquals(t.getSchemaName(), "public");
		assertEquals(t.getName(), "table1");
	}
	
	public void testPrimaryKeys() {
		PGTable t = new PGTable("public", "table1");
		
		assertEquals(t.getPrimaryKey().size(), 0);
		t.addPrimaryKey("field1");
		assertEquals(t.getPrimaryKey().get(0), "field1");
	}
	
	public void testForeignKeys() {
		PGTable t = new PGTable("public", "table1");
		
		assertEquals(t.getForeignKeys().size(), 0);
		
		PGForeignKey fk = new PGForeignKey(t, "table2_field2", "public", "table2", "field2");
		t.addForeignKey(fk);
		
		assertEquals(t.getForeignKeys().size(), 1);
		assertEquals(t.getForeignKeys().get(0), fk);
	}
}
