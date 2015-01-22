package com.inovia.magnifier.rules;

import static org.mockito.Mockito.*;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rule.IndexName;

import junit.framework.*;


public class IndexNameTest extends TestCase {
	public IndexNameTest(String testName) {
        super(testName);
    }

	public static Test suite() {
        return new TestSuite(IndexNameTest.class);
    }
	
	public void testRuleWithIdx() {
		Index mockIndex1 = mock(Index.class);
		when(mockIndex1.getName()).thenReturn("my_index");
		
		List<Index> mockIndexes = new Vector<Index>();
		mockIndexes.add(mockIndex1);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getIndexes()).thenReturn(mockIndexes);
		
		RuleReport rr = new IndexName().run(mockDatabase);
		assertEquals(0.0F, rr.getScore());
		
		Index mockIndex2 = mock(Index.class);
		when(mockIndex2.getName()).thenReturn("other_index_idx");
		
		mockIndexes.add(mockIndex2);
		
		rr = new IndexName().run(mockDatabase);
		assertEquals(50.0F, rr.getScore());
	}
	
	public void testRuleWithPKey() {
		Index mockIndex1 = mock(Index.class);
		when(mockIndex1.getName()).thenReturn("my_index");
		
		List<Index> mockIndexes = new Vector<Index>();
		mockIndexes.add(mockIndex1);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getIndexes()).thenReturn(mockIndexes);
		
		RuleReport rr = new IndexName().run(mockDatabase);
		assertEquals(rr.getScore(), 0.0F);
		
		Index mockIndex2 = mock(Index.class);
		when(mockIndex2.getName()).thenReturn("other_index_pkey");
		
		mockIndexes.add(mockIndex2);
		
		rr = new IndexName().run(mockDatabase);
		assertEquals(rr.getScore(), 50.0F);
	}
	
	public void testRuleWithKey() {
		Index mockIndex1 = mock(Index.class);
		when(mockIndex1.getName()).thenReturn("my_index");
		
		List<Index> mockIndexes = new Vector<Index>();
		mockIndexes.add(mockIndex1);
		
		final Database mockDatabase = mock(Database.class);
		when(mockDatabase.getIndexes()).thenReturn(mockIndexes);
		
		RuleReport rr = new IndexName().run(mockDatabase);
		assertEquals(rr.getScore(), 0.0F);
		
		Index mockIndex2 = mock(Index.class);
		when(mockIndex2.getName()).thenReturn("other_index_key");
		
		mockIndexes.add(mockIndex2);
		
		rr = new IndexName().run(mockDatabase);
		assertEquals(rr.getScore(), 50.0F);
	}
}
