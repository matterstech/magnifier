package com.inovia.magnifier.database;

import com.inovia.magnifier.database.Index;

import junit.framework.*;


public class IndexTest extends TestCase {
	public IndexTest(String testName) {
        super(testName);
    }

	public static Test suite() {
        return new TestSuite(IndexTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Index i = new Index("public", "my_table", "my_index");
		
		assertEquals(i.getSchemaName(), "public");
		assertEquals(i.getTableName(), "my_table");
		assertEquals(i.getName(), "my_index");
	}
}
