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
		ReportEntry goodEntry = new ReportEntry("TABLE public.user",  true);
		assertEquals("TABLE public.user", goodEntry.getEntityDescription());
		assertTrue(goodEntry.isSuccess());
		
		ReportEntry badEntry  = new ReportEntry("TABLE public.group", false);
		assertEquals("TABLE public.group", badEntry.getEntityDescription());
		assertFalse(badEntry.isSuccess());
	}
}
