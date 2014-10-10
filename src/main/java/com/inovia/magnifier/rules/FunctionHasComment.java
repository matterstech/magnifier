package com.inovia.magnifier.rules;

import java.util.*;

import com.inovia.magnifier.database.objects.*;
import com.inovia.magnifier.reports.*;

public abstract class FunctionHasComment {
	public static final String RULE_NAME = "FunctionHasComment";
	public static final String SUGGESTION = "Each function should have a comment explaining what it does";
	public static final Float DEBT = 1F;
	
	public static RuleReport runOn(Vector<Function> functions, Vector<Comment> comments) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Function f : functions) {
			Boolean isSuccess = assertion(f, comments);
			ruleReport.addEntry(new ReportEntry(f.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private static Boolean assertion(Function function, Vector<Comment> comments) {
		for(Comment c : comments) {
			if(c.getSchemaName().equals(function.getSchemaName()) && c.getEntityName().equals(function.getName())) {
				return true;
			}
		}
		
		return false;
	}
}