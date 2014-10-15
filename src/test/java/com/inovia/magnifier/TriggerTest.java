package com.inovia.magnifier;

import com.inovia.magnifier.databaseObjects.Trigger;

import junit.framework.*;

public class TriggerTest extends TestCase {
	public TriggerTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TriggerTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Trigger t = new Trigger("before_update_on_table1_do_stuff", "before", "table1", "update");
		
		assertEquals(t.getName(), "before_update_on_table1_do_stuff");
		assertEquals(t.getActionTiming(), "before");
		assertEquals(t.getTableName(), "table1");
		assertEquals(t.getEventManipulation(), "update");
	}
	
	public void testConstructorWithWrongParameters() {
		try {
			Trigger t = new Trigger(null, "before", "table1", "update");
			fail("should have thrown an exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a trigger should have a name");
		}
		
		try {
			Trigger t = new Trigger("before_update_on_table1_do_stuff", null, "table1", "update");
			fail("should have thrown an exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a trigger should have an action timing");
		}
		
		try {
			Trigger t = new Trigger("before_update_on_table1_do_stuff", "before", null, "update");
			fail("should have thrown an exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a trigger should have a table name");
		}
		
		try {
			Trigger t = new Trigger("before_update_on_table1_do_stuff", "before", "table1", null);
			fail("should have thrown an exception");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a trigger should have an event manipulation");
		}
	}
}
