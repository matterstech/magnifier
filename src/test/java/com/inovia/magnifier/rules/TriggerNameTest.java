package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.Comment;
import com.inovia.magnifier.database.objects.Trigger;
import com.inovia.magnifier.database.postgresql.PGTrigger;
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
		Vector<Trigger> triggers = new Vector<Trigger>();
		
		triggers.add(new PGTrigger("public", "my_table", "my_trigger", "insert", "before"));
		
		RuleReport rr = null;
		rr = TriggerName.runOn(triggers);
		assertEquals(rr.getScore(), 0.0F);
		
		triggers.add(new PGTrigger("public", "my_table", "on_before_insert_my_table", "insert", "before"));
		
		rr = TriggerName.runOn(triggers);
		assertEquals(rr.getScore(), 50.0F);
	}
}
