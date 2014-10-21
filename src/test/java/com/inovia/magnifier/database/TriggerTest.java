package com.inovia.magnifier.database;

import junit.framework.*;

public class TriggerTest extends TestCase {
	public TriggerTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TriggerTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Trigger t = new Trigger("public", "my_table", "my_trigger", "insert", "before");
		
		assertEquals(t.getSchemaName(), "public");
		assertEquals(t.getTableName(), "my_table");
		assertEquals(t.getName(), "my_trigger");
		assertEquals(t.getAction(), "insert");
		assertEquals(t.getTiming(), "before");
	}
}
