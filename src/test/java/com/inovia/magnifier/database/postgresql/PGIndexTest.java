package com.inovia.magnifier.database.postgresql;

import com.inovia.magnifier.database.objects.*;

import junit.framework.*;


public class PGIndexTest extends TestCase {
	public PGIndexTest(String testName) {
        super(testName);
    }

	public static Test suite() {
        return new TestSuite(PGIndexTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Index i = new PGIndex("public", "my_table", "my_index");
		
		assertEquals(i.getSchemaName(), "public");
		assertEquals(i.getTableName(), "my_table");
		assertEquals(i.getName(), "my_index");
	}
}
