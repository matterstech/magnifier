package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.PGComment;
import com.inovia.magnifier.database.postgresql.PGFunction;
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
		Vector<Function> functions = new Vector<Function>();
		Vector<Comment> comments = new Vector<Comment>();
		
		functions.add(new PGFunction("public", "my_function"));
		
		RuleReport rr = null;
		rr = FunctionHasComment.runOn(functions, comments);
		assertEquals(rr.getScore(), 0.0F);
		
		comments.add(new PGComment("public", "my_function", "cool comment about my_function", "function"));
		
		rr = FunctionHasComment.runOn(functions, comments);
		assertEquals(rr.getScore(), 100.0F);
	}
}
