package com.inovia.magnifier.database;

import junit.framework.*;
import static org.mockito.Mockito.*;

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
		
		ForeignKey mockForeignKey = mock(ForeignKey.class);
		when(mockForeignKey.getTable()).thenReturn(t);
		
		t.addForeignKey(mockForeignKey);
		
		assertEquals(1, t.getForeignKeys().size());
		assertEquals(mockForeignKey, t.getForeignKeys().get(0));
	}
}
