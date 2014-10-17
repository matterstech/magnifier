package com.inovia.magnifier.rules;

import java.util.Vector;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

/**
 * The IndexName class aims at checking if the provided
 * index names have the right format
 */
public abstract class IndexName {
	public static final String RULE_NAME = "IndexName";
	public static final String SUGGESTION = "Each index should have a name ending with _idx";
	public static final Float DEBT = 1F;

	public static RuleReport runOn(Vector<Index> indexes) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);

		for(Index i : indexes) {
			Boolean isSuccess = assertion(i);
			ruleReport.addEntry(new ReportEntry(i.getEntityDescription(), isSuccess));
		}

		return ruleReport;
	}

	private static Boolean assertion(Index i) {
		if(i.getName().endsWith("_idx")) { // Inovia convention
			return true;
		} else if(i.getName().endsWith("_pkey") || i.getName().endsWith("_key")) { // Postgres Convention
			return true;
		}
		return false;
	}
}
