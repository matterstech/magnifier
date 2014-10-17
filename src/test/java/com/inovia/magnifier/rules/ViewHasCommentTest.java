package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
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
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         {
				Vector<View> views = new Vector<View>();
				views.add(new PGView("public", "my_view"));
				return views;
			}
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables()       { return null; }
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() { return null; }
			public Vector<Comment> getComments()   {
				Vector<Comment> comments = new Vector<Comment>();
				comments.add(new PGComment("public", "my_view", "cool comment about my_view", "view"));
				return comments;
			}
			public void disconnect()               {  }
			public void connect()                  {  }
		};

		RuleReport rr = new ViewHasComment(database).run();
		assertEquals(rr.getScore(), 100.0F);
	}
}
