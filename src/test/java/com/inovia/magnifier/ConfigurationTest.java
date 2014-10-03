package com.inovia.magnifier;

import junit.framework.*;

public class ConfigurationTest extends TestCase {
	public ConfigurationTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ConfigurationTest.class);
    }
	
	public void testConstructorWithRightParamters() {
		final String[] ARGS = {
				"-h",  "127.0.0.1",
				"-p",  "5432",
				"-t",  "postgresql",
				"-dp", "TODO",
				"-d",  "magnifier_dev",
				"-u",  "postgres",
				"-pw", "postgres",
				"-o",  "TODO"
		};
    	
		Configuration c = new Configuration(ARGS);
		
		assertEquals(c.getHost(),         "127.0.0.1");
		assertEquals(c.getPort(),         "5432");
		assertEquals(c.getDatabaseType(), "postgresql");
		assertEquals(c.getDriverPath(),   "TODO");
		assertEquals(c.getDatabaseName(), "magnifier_dev");
		assertEquals(c.getUser(),         "postgres");
		assertEquals(c.getPassword(),     "postgres");
		assertEquals(c.getReportPath(),   "TODO");
    }
}
