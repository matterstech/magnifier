package com.inovia.magnifier.reports;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.inovia.magnifier.database.Function;
import com.inovia.magnifier.rule.RuleResult;

import junit.framework.*;

public class ReportEntryTest extends TestCase {
	public ReportEntryTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ReportEntryTest.class);
    }
	
	public void testConstructorAndGetters() {
		final RuleResult mockRuleResult = mock(RuleResult.class);
		when(mockRuleResult.isSuccess()).thenReturn(true);
		
		String[] dataToDisplay = {"fake", "oki"};
		ReportEntry goodEntry = new ReportEntry(dataToDisplay,  mockRuleResult);
		assertTrue(goodEntry.isSuccess());
		
		when(mockRuleResult.isSuccess()).thenReturn(false);
		
		ReportEntry badEntry  = new ReportEntry(dataToDisplay, mockRuleResult);
		assertFalse(badEntry.isSuccess());
	}
}
