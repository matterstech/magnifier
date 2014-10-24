package com.inovia.magnifier.reports;

import com.inovia.magnifier.rule.ViewName;

import junit.framework.*;
import static org.mockito.Mockito.*;

public class RuleReportTest extends TestCase {
	public RuleReportTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(RuleReportTest.class);
    }
	
	public void testConstructorAndGetters() {
		RuleReport ruleReport = new RuleReport(ViewName.class, "should be like that", 5F);
		
		assertEquals("ViewName", ruleReport.getRuleName());
		assertEquals("should be like that", ruleReport.getSuggestion());
		assertEquals(100F, ruleReport.getScore());
		
		ReportEntry mockReportEntry = mock(ReportEntry.class);
		when(mockReportEntry.isSuccess()).thenReturn(true);
		ruleReport.addEntry(mockReportEntry);
		assertTrue(ruleReport.getEntries().contains(mockReportEntry));
		assertEquals(100F, ruleReport.getScore());
		assertEquals(0F, ruleReport.getDebt());
		
		ReportEntry mockReportEntry2 = mock(ReportEntry.class);
		when(mockReportEntry2.isSuccess()).thenReturn(false);
		ruleReport.addEntry(mockReportEntry2);
		assertTrue(ruleReport.getEntries().contains(mockReportEntry2));
		assertEquals(50F, ruleReport.getScore());
		assertEquals(5F, ruleReport.getDebt());
	}
}
