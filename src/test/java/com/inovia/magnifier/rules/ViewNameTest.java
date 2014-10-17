package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.Comment;
import com.inovia.magnifier.database.objects.View;
import com.inovia.magnifier.database.postgresql.PGComment;
import com.inovia.magnifier.database.postgresql.PGView;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;


public class ViewNameTest extends TestCase {
	public ViewNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ViewNameTest.class);
    }
	
	public void testRule() {
		Vector<View> views = new Vector<View>();

		views.add(new PGView("public", "my_view"));

		RuleReport rr = null;
		rr = ViewName.runOn(views);
		assertEquals(rr.getScore(), 100.0F);

		views.add(new PGView("public", "my_view_bis"));
		rr = ViewName.runOn(views);
		assertEquals(rr.getScore(), 50.0F);
	}
}
