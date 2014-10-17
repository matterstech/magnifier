package com.inovia.magnifier.rules;

import java.util.Vector;

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
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables()       { return null; }
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      {
				Vector<Index> indexes = new Vector<Index>();
				PGIndex i1 = new PGIndex("public", "my_table", "index1");
				PGIndex i2 = new PGIndex("public", "my_table", "index_idx");
				indexes.add(i1);
				indexes.add(i2);
				return indexes;
			}
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   { return null; }
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = new IndexName(database).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
