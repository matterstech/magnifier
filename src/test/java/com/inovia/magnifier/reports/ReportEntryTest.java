package com.inovia.magnifier.reports;

import static org.mockito.Mockito.mock;

import com.inovia.magnifier.database.Table;

import junit.framework.*;

public class ReportEntryTest extends TestCase {
	public ReportEntryTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ReportEntryTest.class);
    }
	
	public void testConstructorAndGetters() {
		Table mockTable = mock(Table.class);
		
		String[] dataToDisplay = {"fake", "oki"};
		ReportEntry goodEntry = new ReportEntry(mockTable, dataToDisplay,  true);
		assertTrue(goodEntry.isSuccess());
		
		ReportEntry badEntry  = new ReportEntry(mockTable, dataToDisplay, false);
		assertFalse(badEntry.isSuccess());
	}
}
