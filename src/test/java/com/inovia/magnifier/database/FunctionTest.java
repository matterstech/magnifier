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
		
		assertEquals("public", f.getSchemaName());
		assertEquals("my_function", f.getName());
		assertEquals(parameters, f.getParameters());
		
		FunctionParameter p = new FunctionParameter(f, "param_in", "IN");
		f.addParameter(p);
		parameters.add(p);
		
		assertEquals(parameters, f.getParameters());
	}
}
