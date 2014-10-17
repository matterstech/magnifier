package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;

public class TriggerNameTest extends TestCase {
	public TriggerNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TriggerNameTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   {
				Vector<Trigger> triggers = new Vector<Trigger>();
				triggers.add(new PGTrigger("public", "my_table", "my_trigger",                "insert", "before"));
				triggers.add(new PGTrigger("public", "my_table", "on_before_insert_my_table", "insert", "before"));
				return triggers;
			}
			public Vector<Table> getTables()       { return null; }
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   { return null; }
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = new TriggerName(database).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
