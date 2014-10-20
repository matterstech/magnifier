package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.Ruleset;
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
			public List<View> getViews()         { return null; }
			public List<Trigger> getTriggers()   { return null; }
			public List<Table> getTables()       { return null; }
			public List<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public List<Index> getIndexes()      { return null; }
			public List<Function> getFunctions() {
				List<Function> functions = new Vector<Function>();
				functions.add(new PGFunction("public", "my_function"));
				return functions;
			}
			public List<Comment> getComments()   {
				List<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_function", "cool comment about my_function", "function"));
				return comments;
			}
			public void disconnect()               {  }
			public void connect()                  {  }
		};

		class MockRuleSet extends Ruleset {
			public MockRuleSet(Database database) {
				super(database);
			}
			protected List<Rule> getRules() { return null; }
		}		
		MockRuleSet mockRuleSet = new MockRuleSet(database);
		
		RuleReport rr = new FunctionHasComment(mockRuleSet).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
