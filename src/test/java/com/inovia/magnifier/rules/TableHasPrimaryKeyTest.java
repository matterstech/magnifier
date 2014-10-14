package com.inovia.magnifier.rules;

import java.util.Vector;

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
		Vector<Table> tables = new Vector<Table>();
		Vector<Comment> comments = new Vector<Comment>();
		
		PGTable t = new PGTable("public", "my_table");
		tables.add(t);
		
		RuleReport rr = null;
		
		rr = TableHasPrimaryKey.runOn(tables);
		assertEquals(rr.getScore(), 0.0F);
		
		PGTable t2 = new PGTable("public", "my_table");
		t2.addPrimaryKey("id");
		tables.add(t2);
		
		rr = TableHasPrimaryKey.runOn(tables);
		assertEquals(rr.getScore(), 50.0F);
	}
}
