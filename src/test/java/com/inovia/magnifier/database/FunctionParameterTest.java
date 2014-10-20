package com.inovia.magnifier.database;

import junit.framework.*;

public class FunctionParameterTest extends TestCase {
	public FunctionParameterTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(FunctionParameterTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Function f = new Function("public", "my_function");
		FunctionParameter p = new FunctionParameter(f, "param_in", "IN");
		
		assertEquals(p.getFunction(), f);
		assertEquals(p.getName(), "param_in");
		assertEquals(p.getMode(), "IN");
	}
}
