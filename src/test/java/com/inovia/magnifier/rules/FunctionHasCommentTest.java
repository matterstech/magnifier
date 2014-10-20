package com.inovia.magnifier.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;

public class FunctionHasCommentTest extends TestCase {
	public FunctionHasCommentTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(FunctionHasCommentTest.class);
    }
	
	public void testRule() {
		final Function mockFunction1 = mock(Function.class);
		when(mockFunction1.getSchemaName()).thenReturn("public");
		when(mockFunction1.getName()).thenReturn("my_function");
		
		List<Function> mockFunctions = new Vector<Function>();
		mockFunctions.add(mockFunction1);
		
		final Comment mockComment = mock(Comment.class);
		when(mockComment.getSchemaName()).thenReturn("public");
		when(mockComment.getEntityName()).thenReturn("my_function");
		when(mockComment.getEntityType()).thenReturn(Comment.FUNCTION_TYPE);
		
		List<Comment> mockComments = new Vector<Comment>();
		mockComments.add(mockComment);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getFunctions()).thenReturn(mockFunctions);
		when(mockDatabase.getComments()).thenReturn(mockComments);
		
		RuleReport rr = new FunctionHasComment().run(mockDatabase);
		assertEquals(100.0F, rr.getScore());
		
		final Function mockFunction2 = mock(Function.class);
		when(mockFunction2.getSchemaName()).thenReturn("public");
		when(mockFunction2.getName()).thenReturn("your_function");
		
		mockFunctions.add(mockFunction2);
		
		rr = new FunctionHasComment().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
