package com.inovia.magnifier.databaseObjects;

import junit.framework.*;

public class ForeignKeyTest extends TestCase {
	public ForeignKeyTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ForeignKeyTest.class);
    }
	
	public void testConstructorWithRightParamters() {
		ForeignKey fk = new ForeignKey("public", "user_id", "car", "user", "id");
		
		assertEquals(fk.getSchemaName(), "public");
		assertEquals(fk.getName(), "user_id");
		assertEquals(fk.getTableName(), "car");
		assertEquals(fk.getReferencedTableName(), "user");
		assertEquals(fk.getReferencedColumnName(), "id");
	}
}
