package com.inovia.magnifier.databaseObjects;

import java.util.ArrayList;

import junit.framework.*;

public class FunctionTest extends TestCase {
	public FunctionTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FunctionTest.class);
	}

	public void testConstructorWithRightParameters() {
		ArrayList<FunctionParameter> params = new ArrayList<FunctionParameter>();
		Function fp = new Function("name1", params);

		assertEquals(fp.getName(), "name1");
	}

	public void testConstructorWithWrongParameters() {
		try {
			Function fp = new Function("", null);
			fail("exception should be thrown");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "a function should have parameters list, even if empty");
		}
	}
}