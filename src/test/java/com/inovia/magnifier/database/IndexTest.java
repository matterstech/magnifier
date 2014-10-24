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
		
		assertEquals("public", i.getSchemaName());
		assertEquals("my_table", i.getTableName());
		assertEquals("my_index", i.getName());
	}
}
