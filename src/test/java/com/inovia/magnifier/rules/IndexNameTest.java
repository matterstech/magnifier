package com.inovia.magnifier.rules;

import java.util.Vector;

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
		Vector<Index> indexes = new Vector<Index>();
		PGIndex i1 = new PGIndex("public", "my_table", "index1");
		
		indexes.add(i1);
		
		RuleReport rr = null;
		rr = IndexName.runOn(indexes);
		assertEquals(rr.getScore(), 0.0F);
		
		PGIndex i2 = new PGIndex("public", "my_table", "index_idx");
		indexes.add(i2);
		
		rr = IndexName.runOn(indexes);
		assertEquals(rr.getScore(), 50.0F);
	}
}
