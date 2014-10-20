package com.inovia.magnifier.rules;

import static org.mockito.Mockito.*;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rule.TableHasPrimaryKey;

import junit.framework.*;


public class TableHasPrimaryKeyTest extends TestCase {
	public TableHasPrimaryKeyTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(TableHasPrimaryKeyTest.class);
    }
	
	public void testRule() {
		Table mockTable = mock(Table.class);
		when(mockTable.hasPrimaryKey()).thenReturn(false);
		
		List<Table> mockTables = new Vector<Table>();
		mockTables.add(mockTable);
		
		Database mockDatabase = mock(Database.class);
		when(mockDatabase.getTables()).thenReturn(mockTables);
		
		RuleReport rr = new TableHasPrimaryKey().run(mockDatabase);
		assertEquals(0.0F, rr.getScore());
		
		when(mockTable.hasPrimaryKey()).thenReturn(true);
		
		rr = new TableHasPrimaryKey().run(mockDatabase);
		assertEquals(100.0F, rr.getScore());
	}
}
