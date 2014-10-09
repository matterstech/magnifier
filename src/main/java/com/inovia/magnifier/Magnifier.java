package com.inovia.magnifier;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rules.*;

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
			
			Report r = FunctionHasComment.runOn(database.getFunctions(), database.getComments());
//			System.out.println(r.getEntries());
			
			r = FunctionParameterName.runOn(database.getFunctions());
			System.out.println(r.getEntries());
			
			// r.generateHtml(configuration.getReportPath());
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
