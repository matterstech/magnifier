package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.Ruleset;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.*;

import junit.framework.*;


public class IndexNameTest extends TestCase {
	public IndexNameTest(String testName) {
        super(testName);
    }

	public static Test suite() {
        return new TestSuite(IndexNameTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public List<View> getViews()         { return null; }
			public List<Trigger> getTriggers()   { return null; }
			public List<Table> getTables()       { return null; }
			public List<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public List<Index> getIndexes()      {
				List<Index> indexes = new Vector<Index>();
				PGIndex i1 = new PGIndex("public", "my_table", "index1");
				PGIndex i2 = new PGIndex("public", "my_table", "index_idx");
				indexes.add(i1);
				indexes.add(i2);
				return indexes;
			}
			public List<Function> getFunctions() { return null; }
			public List<Comment> getComments()   { return null; }
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
		
		RuleReport rr = new IndexName(mockRuleSet).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
