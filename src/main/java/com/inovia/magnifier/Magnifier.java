package com.inovia.magnifier;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rules.*;

public class Magnifier {
	public static void main(String[] args) {
		Configuration configuration = null;
		Database database = null;
		try {
			configuration = new Configuration(args);
			database = DatabaseFactory.getDatabase(
					configuration.getDriverPath(),
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
			
			Date startDate = new Date();
			
			report.addRuleReport(TableHasComment.runOn(database.getTables(), database.getComments()));
			report.addRuleReport(IndexName.runOn(database.getIndexes()));
			report.addRuleReport(ForeignKeyName.runOn(database.getTables()));
			report.addRuleReport(TableHasPrimaryKey.runOn(database.getTables()));
			report.addRuleReport(FunctionParameterName.runOn(database.getFunctions()));
			report.addRuleReport(FunctionHasComment.runOn(database.getFunctions(), database.getComments()));
			report.addRuleReport(ViewName.runOn(database.getViews()));
			report.addRuleReport(ViewHasComment.runOn(database.getViews(), database.getComments()));
			report.addRuleReport(TriggerName.runOn(database.getTriggers()));
			report.addRuleReport(TriggerHasComment.runOn(database.getTriggers(), database.getComments()));
			
			Date endDate = new Date();
			report.generateHtml(configuration.getReportPath(), startDate, endDate);
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
