package com.inovia.magnifier.database;

import junit.framework.*;

public class SchemaTest extends TestCase {
	public SchemaTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(SchemaTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Schema s = new Schema("public");
		
		assertEquals("public", s.getName());
	}
}
