package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.Ruleset;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;


public class ViewHasCommentTest extends TestCase {
	public ViewHasCommentTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ViewHasCommentTest.class);
	}

	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public List<View> getViews()         {
				List<View> views = new Vector<View>();
				views.add(new PGView("public", "my_view"));
				return views;
			}
			public List<Trigger> getTriggers()   { return null; }
			public List<Table> getTables()       { return null; }
			public List<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public List<Index> getIndexes()      { return null; }
			public List<Function> getFunctions() { return null; }
			public List<Comment> getComments()   {
				List<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_view", "cool comment about my_view", "view"));
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
		
		RuleReport rr = new ViewHasComment(mockRuleSet).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
