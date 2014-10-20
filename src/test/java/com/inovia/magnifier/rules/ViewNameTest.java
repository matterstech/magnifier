package com.inovia.magnifier.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
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
		final View mockView1 = mock(View.class);
		when(mockView1.getSchemaName()).thenReturn("public");
		when(mockView1.getName()).thenReturn("my_view");
		
		List<View> mockViews = new Vector<View>();
		mockViews.add(mockView1);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getViews()).thenReturn(mockViews);
		
		RuleReport rr = new ViewName().run(mockDatabase);
		assertEquals(100.0F, rr.getScore());
		
		final View mockView2 = mock(View.class);
		when(mockView2.getSchemaName()).thenReturn("public");
		when(mockView2.getName()).thenReturn("view_of_something");
		
		mockViews.add(mockView2);
		
		rr = new ViewName().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
