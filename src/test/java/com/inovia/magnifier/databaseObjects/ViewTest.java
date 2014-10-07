package com.inovia.magnifier.databaseObjects;

import junit.framework.*;

public class ViewTest extends TestCase {
	public ViewTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ViewTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		View view = new View("public", "view1");
		
		assertEquals(view.getSchema(), "public");
		assertEquals(view.getName(), "view1");
		assertEquals(view.toString(), "public.view1");
	}
	
	public void testConstructorWithWrongParameters() {
		try {
			View view = new View("public", null);
			fail("should have thrown exception.");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "A view needs a name");
		}
		
		try {
			View view = new View(null, "view1");
			fail("should have thrown exception.");
		} catch(IllegalArgumentException e) {
			assertEquals(e.getMessage(), "A view needs a schema");
		}
	}
}
