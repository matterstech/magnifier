package com.inovia.magnifier.databaseObjects;

import junit.framework.*;

public class FunctionParameterTest extends TestCase {
	public FunctionParameterTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FunctionParameterTest.class);
	}

	public void testConstructorWithRightParameters() {
		FunctionParameter fp = new FunctionParameter("name1", "integer", true);

		assertEquals(fp.getName(), "name1");
		assertEquals(fp.getType(), "integer");
		assertTrue(fp.isIn());
		assertFalse(fp.isOut());
	}

	public void testConstructorWithWrongParameters() {
		try {
			FunctionParameter fp = new FunctionParameter("", "", true);
			fail("exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a function parameter should have a type");
		}

		try {
			FunctionParameter fp = new FunctionParameter("name", "integer", null);
			fail("exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a function parameter should be INPUT or OUTPUT");
		}
	}
}