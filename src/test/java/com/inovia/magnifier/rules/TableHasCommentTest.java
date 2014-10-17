package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;

public class TableHasCommentTest extends TestCase {
	public TableHasCommentTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TableHasCommentTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables() {
				Vector<Table> tables = new Vector<Table>();
				tables.add(new PGTable("public", "my_table"));
				return tables;
			}
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   {
				Vector<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_table", "cool comment about my_table", "table"));
				return comments;
			}
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = new TableHasComment(database).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
