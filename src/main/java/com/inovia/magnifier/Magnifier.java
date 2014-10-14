package com.inovia.magnifier;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.database.objects.Table;
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
			
			Report report = new Report(database.getName());
			report.addRuleReport(FunctionHasComment.runOn(database.getFunctions(), database.getComments()));
			report.addRuleReport(FunctionParameterName.runOn(database.getFunctions()));
			report.addRuleReport(TableHasComment.runOn(database.getTables(), database.getComments()));
			report.addRuleReport(TableHasPrimaryKey.runOn(database.getTables()));
			
			/*for(Table t : database.getTables()) {
				System.out.println(t.getName() + ": " + t.getPrimaryKey());
				System.out.println(t.getForeignKeys());
				System.out.println();
			}*/
			
			report.addRuleReport(ForeignKeyName.runOn(database.getTables()));
			
			report.generateHtml(configuration.getReportPath());
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
