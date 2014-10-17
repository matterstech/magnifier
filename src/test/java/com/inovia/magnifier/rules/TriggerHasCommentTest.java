package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.*;

import junit.framework.*;


public class TriggerHasCommentTest extends TestCase {
	public TriggerHasCommentTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TriggerHasCommentTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   {
				Vector<Trigger> triggers = new Vector<Trigger>();
				triggers.add(new PGTrigger("public", "my_table", "my_trigger", "insert", "before"));
				return triggers;
			}
			public Vector<Table> getTables()       { return null; }
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   {
				Vector<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_trigger", "cool comment about my_trigger", "trigger"));
				return comments;
			}
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = new TriggerHasComment(database).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
