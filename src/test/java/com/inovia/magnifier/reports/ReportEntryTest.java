package com.inovia.magnifier.reports;

import junit.framework.*;

public class ReportEntryTest extends TestCase {
	public ReportEntryTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ReportEntryTest.class);
    }
	
	public void testConstructorAndGetters() {
		String[] dataToDisplay = {"fake", "oki"};
		ReportEntry goodEntry = new ReportEntry(dataToDisplay,  true);
		assertTrue(goodEntry.isSuccess());
		
		ReportEntry badEntry  = new ReportEntry(dataToDisplay, false);
		assertFalse(badEntry.isSuccess());
	}
}
