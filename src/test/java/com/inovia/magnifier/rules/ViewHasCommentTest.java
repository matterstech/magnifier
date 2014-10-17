package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.PGComment;
import com.inovia.magnifier.database.postgresql.PGView;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;


public class ViewHasCommentTest extends TestCase {
	public ViewHasCommentTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(ViewHasCommentTest.class);
	}

	public void testRule() {
		Vector<View> views = new Vector<View>();
		Vector<Comment> comments = new Vector<Comment>();

		views.add(new PGView("public", "my_view"));

		RuleReport rr = null;
		rr = ViewHasComment.runOn(views, comments);
		assertEquals(rr.getScore(), 0.0F);

		comments.add(new PGComment("public", "my_view", "cool comment about my_view", "view"));
		
		rr = ViewHasComment.runOn(views, comments);
		assertEquals(rr.getScore(), 100.0F);
	}
}
