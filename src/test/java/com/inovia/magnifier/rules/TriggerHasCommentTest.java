package com.inovia.magnifier.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
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
		final Trigger mockTrigger1 = mock(Trigger.class);
		when(mockTrigger1.getSchemaName()).thenReturn("public");
		when(mockTrigger1.getName()).thenReturn("my_trigger");
		
		List<Trigger> mockTriggers = new Vector<Trigger>();
		mockTriggers.add(mockTrigger1);
		
		final Comment mockComment = mock(Comment.class);
		when(mockComment.getSchemaName()).thenReturn("public");
		when(mockComment.getEntityName()).thenReturn("my_trigger");
		when(mockComment.getEntityType()).thenReturn(Comment.TRIGGER_TYPE);
		
		List<Comment> mockComments = new Vector<Comment>();
		mockComments.add(mockComment);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getTriggers()).thenReturn(mockTriggers);
		when(mockDatabase.getComments()).thenReturn(mockComments);
		
		RuleReport rr = new TriggerHasComment().run(mockDatabase);
		assertEquals(100.0F, rr.getScore());
		
		final Trigger mockTrigger2 = mock(Trigger.class);
		when(mockTrigger2.getSchemaName()).thenReturn("public");
		when(mockTrigger2.getName()).thenReturn("your_trigger");
		
		mockTriggers.add(mockTrigger2);
		
		rr = new TriggerHasComment().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
