package com.inovia.magnifier.rules;

import java.util.Vector;

import junit.framework.*;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.*;

public class FunctionParameterNameTest extends TestCase {
	public FunctionParameterNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(FunctionParameterNameTest.class);
    }
	
	public void testRule() {
		Vector<Function> functions = new Vector<Function>();
		
		PGFunction f = new PGFunction("public", "my_function");
		functions.add(f);
		
		RuleReport rr = null;
		rr = FunctionParameterName.runOn(functions);
		System.out.println(rr.getScore());
		assertEquals(rr.getScore(), 100.0F); // Because no parameters in the function
		
		f.addParameter(new PGFunctionParameter(f, "param1_in", "IN"));
		rr = FunctionParameterName.runOn(functions);
		assertEquals(rr.getScore(), 100.0F);
		
		f.addParameter(new PGFunctionParameter(f, "param2", "OUT"));
		rr = FunctionParameterName.runOn(functions);
		assertEquals(rr.getScore(), 50.0F);
	}
}
