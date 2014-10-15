package com.inovia.magnifier.database.postgresql;

import java.util.Vector;

import junit.framework.*;


public class PGFunctionTest extends TestCase {
	public PGFunctionTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PGFunctionTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		PGFunction f = new PGFunction("public", "my_function");
		
		Vector<PGFunctionParameter> parameters = new Vector<PGFunctionParameter>();
		
		assertEquals(f.getSchemaName(), "public");
		assertEquals(f.getName(), "my_function");
		assertEquals(f.getParameters(), parameters);
		
		PGFunctionParameter p = new PGFunctionParameter(f, "param_in", "IN");
		f.addParameter(p);
		parameters.add(p);
		
		assertEquals(f.getParameters(), parameters);
	}
}
