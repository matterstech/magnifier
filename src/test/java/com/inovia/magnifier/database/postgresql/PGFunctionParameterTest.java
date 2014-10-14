package com.inovia.magnifier.database.postgresql;

import junit.framework.*;

public class PGFunctionParameterTest extends TestCase {
	public PGFunctionParameterTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PGFunctionParameterTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		PGFunction f = new PGFunction("public", "my_function");
		PGFunctionParameter p = new PGFunctionParameter(f, "param_in", "IN");
		
		assertEquals(p.getFunction(), f);
		assertEquals(p.getName(), "param_in");
		assertEquals(p.getMode(), "IN");
	}
}
