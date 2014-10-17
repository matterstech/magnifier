package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.PGForeignKey;
import com.inovia.magnifier.database.postgresql.PGTable;
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
		Vector<Table> tables = new Vector<Table>();
		
		PGTable t = new PGTable("public", "my_table");
		
		t.addForeignKey(new PGForeignKey(t, "table2_field2_stuff", "public", "table2", "field2"));
		tables.add(t);
		
		RuleReport rr = null;
		
		rr = ForeignKeyName.runOn(tables);
		assertEquals(rr.getScore(), 0.0F);
		
		
		t.addForeignKey(new PGForeignKey(t, "table2_field2", "public", "table2", "field2"));
		
		rr = ForeignKeyName.runOn(tables);
		assertEquals(rr.getScore(), 50.0F);
	}
}
