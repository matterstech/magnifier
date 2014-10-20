package com.inovia.magnifier.rules;

import static org.mockito.Mockito.*;

import java.util.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.RuleReport;

import junit.framework.*;

public class TableHasCommentTest extends TestCase {
	public TableHasCommentTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TableHasCommentTest.class);
    }
	
	public void testRule() {
		final Table mockTable1 = mock(Table.class);
		when(mockTable1.getSchemaName()).thenReturn("public");
		when(mockTable1.getName()).thenReturn("my_table");
		
		List<Table> mockTables = new Vector<Table>();
		mockTables.add(mockTable1);
		
		final Comment mockComment = mock(Comment.class);
		when(mockComment.getSchemaName()).thenReturn("public");
		when(mockComment.getEntityName()).thenReturn("my_table");
		when(mockComment.getEntityType()).thenReturn(Comment.TABLE_TYPE);
		
		List<Comment> mockComments = new Vector<Comment>();
		mockComments.add(mockComment);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getTables()).thenReturn(mockTables);
		when(mockDatabase.getComments()).thenReturn(mockComments);
		
		RuleReport rr = new TableHasComment().run(mockDatabase);
		assertEquals(100.0F, rr.getScore());
		
		final Table mockTable2 = mock(Table.class);
		when(mockTable2.getSchemaName()).thenReturn("public");
		when(mockTable2.getName()).thenReturn("your_table");
		
		mockTables.add(mockTable2);
		
		rr = new TableHasComment().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
