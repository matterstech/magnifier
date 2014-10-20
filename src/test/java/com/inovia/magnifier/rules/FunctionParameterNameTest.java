package com.inovia.magnifier.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import junit.framework.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public class FunctionParameterNameTest extends TestCase {
	public FunctionParameterNameTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(FunctionParameterNameTest.class);
	}

	public void testRule() {
		final FunctionParameter mockFunctionParameter = mock(FunctionParameter.class);
		when(mockFunctionParameter.getName()).thenReturn("param1");
		when(mockFunctionParameter.getMode()).thenReturn("IN");
		
		List<FunctionParameter> mockFunctionParameters = new Vector<FunctionParameter>();
		mockFunctionParameters.add(mockFunctionParameter);
		
		final Function mockFunction = mock(Function.class);
		when(mockFunction.getParameters()).thenReturn(mockFunctionParameters);
		
		List<Function> mockFunctions = new Vector<Function>();
		mockFunctions.add(mockFunction);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getFunctions()).thenReturn(mockFunctions);

		RuleReport rr = new FunctionParameterName().run(mockDatabase);
		assertEquals(0.0F, rr.getScore());
		
		final FunctionParameter mockFunctionParameter2 = mock(FunctionParameter.class);
		when(mockFunctionParameter.getName()).thenReturn("param2_out");
		when(mockFunctionParameter.getMode()).thenReturn("out");
		
		mockFunctionParameters.add(mockFunctionParameter2);
		
		rr = new FunctionParameterName().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
