package com.inovia.magnifier;

import org.apache.commons.cli.ParseException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConfigurationTest extends TestCase {
	public ConfigurationTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ConfigurationTest.class);
    }
	
	public void testConstructorAndGetters() {
		final String[] ARGS = {
				"-host",     "127.0.0.1",
				"-port",     "5432",
				"-dbms",     "postgresql",
				"-driver",   "TODO",
				"-database", "magnifier_dev",
				"-user",     "postgres",
				"-password", "postgres",
				"-output",   "TODO"
		};
    	
		try {
		Configuration c = new Configuration(ARGS);
		c.parseCommandLine();
		
		assertEquals(c.getHost(),         "127.0.0.1");
		assertEquals(c.getPort(),         "5432");
		assertEquals(c.getDatabaseType(), "postgresql");
		assertEquals(c.getDriverPath(),   "TODO");
		assertEquals(c.getDatabaseName(), "magnifier_dev");
		assertEquals(c.getUser(),         "postgres");
		assertEquals(c.getPassword(),     "postgres");
		assertEquals(c.getReportPath(),   "TODO");
		} catch(ParseException e) {
			fail("should not throw ParseException");
		}
    }
}
