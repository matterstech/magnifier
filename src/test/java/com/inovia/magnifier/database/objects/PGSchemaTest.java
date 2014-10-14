package com.inovia.magnifier.database.objects;

import com.inovia.magnifier.database.postgresql.*;

import junit.framework.*;

public class PGSchemaTest extends TestCase {
	public PGSchemaTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(PGSchemaTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		PGSchema s = new PGSchema("public");
		
		assertEquals(s.getName(), "public");
	}
}
