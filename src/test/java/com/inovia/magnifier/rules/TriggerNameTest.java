package com.inovia.magnifier.rules;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.RuleReport;
import com.inovia.magnifier.rule.TriggerName;

import junit.framework.*;

public class TriggerNameTest extends TestCase {
	public TriggerNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TriggerNameTest.class);
    }
	
	public void testRule() {
		Trigger mockTrigger1 = mock(Trigger.class);
		when(mockTrigger1.getName()).thenReturn("my_trigger_on_user");
		when(mockTrigger1.getTableName()).thenReturn("user");
		when(mockTrigger1.getTiming()).thenReturn("BEFORE");
		when(mockTrigger1.getAction()).thenReturn("UPDATE");
		
		List<Trigger> mockTriggers = new Vector<Trigger>();
		mockTriggers.add(mockTrigger1);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getTriggers()).thenReturn(mockTriggers);
		
		RuleReport rr = new TriggerName().run(mockDatabase);
		assertEquals(0.0F, rr.getScore());
		
		Trigger mockTrigger2 = mock(Trigger.class);
		when(mockTrigger2.getName()).thenReturn("on_before_update_user");
		when(mockTrigger2.getTableName()).thenReturn("user");
		when(mockTrigger2.getTiming()).thenReturn("BEFORE");
		when(mockTrigger2.getAction()).thenReturn("UPDATE");
		
		mockTriggers.add(mockTrigger2);
		
		rr = new TriggerName().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
