package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
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
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables()       { return null; }
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() {
				Vector<Function> functions = new Vector<Function>();
				functions.add(new PGFunction("public", "my_function"));
				return functions;
			}
			public Vector<Comment> getComments()   {
				Vector<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_function", "cool comment about my_function", "function"));
				return comments;
			}
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = null;
		rr = new FunctionHasComment(database).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
