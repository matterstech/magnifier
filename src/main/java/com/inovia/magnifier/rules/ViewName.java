package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The ViewName class aims at checking if the provided
 * view names have the right format
 */
public abstract class ViewName {
	public static final String RULE_NAME = "ViewName";
	public static final String SUGGESTION = "Each view should have a name ending with _view";
	public static final Float DEBT = 1F;

	private static final String SUFFIX = "_view";

	public static RuleReport runOn(Vector<View> views) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(View v : views) {
			Boolean isSuccess = assertion(v);
			ruleReport.addEntry(new ReportEntry(v.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private static Boolean assertion(View view) {
		if(view.getName().endsWith(SUFFIX)) {
			return true;
		}

		return false;
	}
}
