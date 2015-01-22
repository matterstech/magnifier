package com.inovia.magnifier.database;

import junit.framework.*;
import static org.mockito.Mockito.*;

public class ForeignKeyTest extends TestCase {
	public ForeignKeyTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ForeignKeyTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Table mockTable = mock(Table.class);
		
		ForeignKey fk = new ForeignKey(mockTable, "table1_field1", "public", "table1", "field1");
		
		assertEquals(mockTable, fk.getTable());
		assertEquals("table1_field1", fk.getColumnName());
		assertEquals("public", fk.getForeignSchemaName());
		assertEquals("table1", fk.getForeignTableName());
		assertEquals("field1", fk.getForeignColumnName());
	}
}
