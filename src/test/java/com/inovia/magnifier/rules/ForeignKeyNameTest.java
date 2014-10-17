package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;


public class ForeignKeyNameTest extends TestCase {
	public ForeignKeyNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ForeignKeyNameTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables() {
				Vector<Table> tables = new Vector<Table>();
				
				PGTable t = new PGTable("public", "my_table");
				t.addForeignKey(new PGForeignKey(t, "table2_field2_stuff", "public", "table2", "field2"));
				t.addForeignKey(new PGForeignKey(t, "table2_field2",       "public", "table2", "field2"));
				
				tables.add(t);
				return tables;
			}
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   { return null; }
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = null;
		
		rr = new ForeignKeyName(database).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
