package com.inovia.magnifier.reports;

import java.util.*;
import junit.framework.*;
import static org.mockito.Mockito.*;

public class ReportTest extends TestCase {
	public ReportTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ReportTest.class);
    }
	
	public void testConstructor() {
		List<RuleReport> ruleReports = new Vector<RuleReport>();
		Report report = new Report("my_database");
		
		assertEquals(ruleReports, report.getRuleReports());
		
		RuleReport mockRuleReport = mock(RuleReport.class);
		report.addRuleReport(mockRuleReport);
		assertEquals(mockRuleReport, report.getRuleReports().get(0));
	}
}
