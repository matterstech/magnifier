package com.inovia.magnifier.rules;

import java.util.Vector;

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
		Vector<Table> tables = new Vector<Table>();
		Vector<Comment> comments = new Vector<Comment>();
		
		// We just create a table without adding any comment
		PGTable t = new PGTable("public", "my_table");
		tables.add(t);
		
		RuleReport rr = null;
		
		rr = TableHasComment.runOn(tables, comments);
		// We test it
		assertEquals(rr.getScore(), 0F);
		
		// Now we create a comment
		PGComment c = new PGComment("public", "my_table", "cool comment about my_table", "table");
		comments.add(c);
		
		rr = TableHasComment.runOn(tables, comments);
		assertEquals(rr.getScore(), 100F);
	}
}
