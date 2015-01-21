package com.inovia.magnifier.rule;

import java.util.List;

import com.inovia.magnifier.database.Comment;
import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.Function;
import com.inovia.magnifier.reports.ReportEntry;
import com.inovia.magnifier.reports.RuleReport;

/**
 * The FunctionHasComment class aims at checking if
 * the provided functions all have a comment
 */
public class FunctionHasComment implements Rule {
	public static final String RULE_NAME = "FunctionHasComment";
	public static final String SUGGESTION = "Each function should have a comment explaining what it does";
	public static final Float DEBT = 1F;
	public static final String[] FORMAT = {"schema", "function"};

	public FunctionHasComment() {  }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(this, SUGGESTION, DEBT);
		
		for(Function f : database.getFunctions()) {
			RuleResult result = assertion(f, database.getComments());
			String[] dataToDisplay = {f.getSchemaName(), f.getName()};
			ruleReport.addEntry(new ReportEntry(dataToDisplay, result));
		}
		
		return ruleReport;
	}
	
	private RuleResult assertion(Function function, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals(Comment.FUNCTION_TYPE)
					&& c.getSchemaName().equals(function.getSchemaName())
					&& c.getEntityName().equals(function.getName())) {
				return new RuleResult(true, null);
			}
		}
		
		return new RuleResult(false, null);
	}
	
	public String[] getRuleReportFormat() {
		return FORMAT;
	}

	public String getName() {
		return RULE_NAME;
	}
}
