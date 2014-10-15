package com.inovia.magnifier.database.postgresql;

import junit.framework.*;

public class PGTriggerTest extends TestCase {
	public PGTriggerTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PGTriggerTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		PGTrigger t = new PGTrigger("public", "my_table", "my_trigger", "insert", "before");
		
		assertEquals(t.getSchemaName(), "public");
		assertEquals(t.getTableName(), "my_table");
		assertEquals(t.getName(), "my_trigger");
		assertEquals(t.getAction(), "insert");
		assertEquals(t.getTiming(), "before");
	}
}
