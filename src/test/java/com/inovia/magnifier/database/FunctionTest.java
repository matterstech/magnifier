package com.inovia.magnifier.database;

import java.util.Vector;

import junit.framework.*;


public class FunctionTest extends TestCase {
	public FunctionTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(FunctionTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Function f = new Function("public", "my_function");
		
		Vector<FunctionParameter> parameters = new Vector<FunctionParameter>();
		
		assertEquals(f.getSchemaName(), "public");
		assertEquals(f.getName(), "my_function");
		assertEquals(f.getParameters(), parameters);
		
		FunctionParameter p = new FunctionParameter(f, "param_in", "IN");
		f.addParameter(p);
		parameters.add(p);
		
		assertEquals(f.getParameters(), parameters);
	}
}
