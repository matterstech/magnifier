package com.inovia.magnifier;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.database.postgresql.*;
import com.inovia.magnifier.reports.Report;
import com.inovia.magnifier.rules.FunctionHasComment;

public class Magnifier {
	public static void main(String[] args) {
		Configuration configuration = null;
		Database database = null;
		try {
			configuration = new Configuration(args);
			database = DatabaseFactory.getDatabase(
					configuration.getDatabaseType(),
					configuration.getHost(),
					configuration.getPort(),
					configuration.getDatabaseName(),
					configuration.getUser(),
					configuration.getPassword());
			
			// Bootstrap
			database.connect();
			database.load();
			
			System.out.println("Schemas:");
			for(Schema s : database.getSchemas()) {
				System.out.println(s);
			}
			System.out.println();
			
			System.out.println("Functions:");
			for(Function f : database.getFunctions()) {
				System.out.println(f);
			}
			System.out.println();
			
			System.out.println("Comments:");
			for(Comment c : database.getComments()) {
				System.out.println(c);
			}
			System.out.println();
			
			Report r = FunctionHasComment.runOn(database.getFunctions(), database.getComments());
			System.out.println(r.getEntries());
			
			r.generateHtml();
		} catch(UnsupportedOperationException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if(database != null) {
				database.disconnect();
			}
		}
	}
}
