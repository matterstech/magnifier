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
		
		assertEquals("public", t.getSchemaName());
		assertEquals("my_table", t.getTableName());
		assertEquals("my_trigger", t.getName());
		assertEquals("insert", t.getAction());
		assertEquals("before", t.getTiming());
	}
}
