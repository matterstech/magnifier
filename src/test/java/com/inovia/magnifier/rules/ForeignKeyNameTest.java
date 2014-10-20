package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

import junit.framework.*;
import static org.mockito.Mockito.*;

public class ForeignKeyNameTest extends TestCase {
	public ForeignKeyNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(ForeignKeyNameTest.class);
    }
	
	public void testRule() {
		List<ForeignKey> mockForeignKeys = new Vector<ForeignKey>();
		
		final ForeignKey mockForeignKey1 = mock(ForeignKey.class);
		when(mockForeignKey1.getColumnName()).thenReturn("my_foreign_key");
		when(mockForeignKey1.getForeignTableName()).thenReturn("user");
		when(mockForeignKey1.getForeignColumnName()).thenReturn("id");
		mockForeignKeys.add(mockForeignKey1);
		
		final ForeignKey mockForeignKey2 = mock(ForeignKey.class);
		when(mockForeignKey2.getColumnName()).thenReturn("user_id");
		when(mockForeignKey2.getForeignTableName()).thenReturn("user");
		when(mockForeignKey2.getForeignColumnName()).thenReturn("id");
		mockForeignKeys.add(mockForeignKey2);
		
		final Table mockTable = mock(Table.class);
		when(mockTable.getForeignKeys()).thenReturn(mockForeignKeys);
		when(mockTable.getName()).thenReturn("group");
		List<Table> mockTables = new Vector<Table>();
		mockTables.add(mockTable);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getTables()).thenReturn(mockTables);
		
		RuleReport rr = new ForeignKeyName().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
}
