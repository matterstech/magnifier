package com.inovia.magnifier.database;

import junit.framework.*;


public class ViewTest extends TestCase {
	public ViewTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ViewTest.class);
	}

	public void testConstructorWithRightParameters() {
		View v = new View("public", "my_view");
		
		assertEquals(v.getSchemaName(), "public");
		assertEquals(v.getName(), "my_view");
	}
}
