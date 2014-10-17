package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.PGComment;
import com.inovia.magnifier.database.postgresql.PGTrigger;
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
		Vector<Trigger> triggers = new Vector<Trigger>();
		Vector<Comment> comments = new Vector<Comment>();
		
		triggers.add(new PGTrigger("public", "my_table", "my_trigger", "insert", "before"));
		
		RuleReport rr = null;
		rr = TriggerHasComment.runOn(triggers, comments);
		assertEquals(rr.getScore(), 0.0F);
		
		comments.add(new PGComment("public", "my_trigger", "cool comment about my_trigger", "trigger"));
		
		rr = TriggerHasComment.runOn(triggers, comments);
		assertEquals(rr.getScore(), 100.0F);
	}
}
