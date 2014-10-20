package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.Rule;
import com.inovia.magnifier.Ruleset;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.*;

import junit.framework.*;


public class TableHasPrimaryKeyTest extends TestCase {
	public TableHasPrimaryKeyTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TableHasPrimaryKeyTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                   {  }
			public List<View> getViews()         { return null; }
			public List<Trigger> getTriggers()   { return null; }
			public List<Table> getTables()       {
				List<Table> tables = new Vector<Table>();
				PGTable t = new PGTable("public", "my_table");
				PGTable t2 = new PGTable("public", "my_table");
				t2.addPrimaryKey("id");
				
				tables.add(t);
				tables.add(t2);
				return tables;
			}
			public List<Schema> getSchemas()     { return null; }
			public String getName()              { return null; }
			public List<Index> getIndexes()      { return null; }
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
		
		RuleReport rr = new TableHasPrimaryKey(mockRuleSet).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
