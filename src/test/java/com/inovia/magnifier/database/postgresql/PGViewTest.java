package com.inovia.magnifier.database.postgresql;

import junit.framework.*;


public class PGViewTest extends TestCase {
	public PGViewTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(PGViewTest.class);
	}

	public void testConstructorWithRightParameters() {
		PGView v = new PGView("public", "my_view");
		
		assertEquals(v.getSchemaName(), "public");
		assertEquals(v.getName(), "my_view");
	}
}
