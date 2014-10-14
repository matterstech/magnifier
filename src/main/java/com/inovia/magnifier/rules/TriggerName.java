package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public class TriggerName {
	public static final String RULE_NAME = "TriggerName";
	public static final String SUGGESTION = "Each trigger name should match on_when_what_tablename (example: on_before_update_user)";
	public static final Float DEBT = 1F;

	public static RuleReport runOn(Vector<Trigger> triggers) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Trigger t : triggers) {
			Boolean isSuccess = assertion(t);
			ruleReport.addEntry(new ReportEntry(t.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private static Boolean assertion(Trigger t) {
		final String PATTERN = "on_" + t.getTiming() + "_" + t.getAction() + "_" + t.getTableName();
		return t.getName().equalsIgnoreCase(PATTERN);
	}
}
