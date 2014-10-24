package com.inovia.magnifier.database;

import junit.framework.*;

public class CommentTest extends TestCase {
	public CommentTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(CommentTest.class);
    }
	
	public void testConstructorWithRightParameters() {
		Comment comment = new Comment("public", "user", "awesome comment", Comment.TABLE_TYPE);
		
		assertEquals("public", comment.getSchemaName());
		assertEquals("user", comment.getEntityName());
		assertEquals("awesome comment", comment.getContent());
		assertEquals(Comment.TABLE_TYPE, comment.getEntityType());
	}
}
