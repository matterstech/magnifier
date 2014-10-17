package com.inovia.magnifier.rules;

import java.util.Vector;

import junit.framework.*;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.*;

public class FunctionParameterNameTest extends TestCase {
	public FunctionParameterNameTest(String testName) {
        super(testName);
    }
	
	public static Test suite() {
        return new TestSuite(FunctionParameterNameTest.class);
    }
	
	public void testRule() {
		Database database = new Database() {
			public void load()                     {  }
			public Vector<View> getViews()         { return null; }
			public Vector<Trigger> getTriggers()   { return null; }
			public Vector<Table> getTables() {
				Vector<Table> tables = new Vector<Table>();
				
				PGTable t = new PGTable("public", "my_table");
				t.addForeignKey(new PGForeignKey(t, "table2_field2_stuff", "public", "table2", "field2"));
				t.addForeignKey(new PGForeignKey(t, "table2_field2",       "public", "table2", "field2"));
				
				tables.add(t);
				return tables;
			}
			public Vector<Schema> getSchemas()     { return null; }
			public String getName()                { return null; }
			public Vector<Index> getIndexes()      { return null; }
			public Vector<Function> getFunctions() {
				Vector<Function> functions = new Vector<Function>();
				PGFunction f = new PGFunction("public", "my_function");
				f.addParameter(new PGFunctionParameter(f, "param1_in", "IN"));
				f.addParameter(new PGFunctionParameter(f, "param2",    "OUT"));
				
				
				functions.add(f);
				return functions;
			}
			public Vector<Comment> getComments()   { return null; }
			public void disconnect()               {  }
			public void connect()                  {  }
		};
		
		RuleReport rr = new FunctionParameterName(database).run();
		assertEquals(rr.getScore(), 50.0F);
	}
}
