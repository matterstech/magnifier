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

	public FunctionHasComment() {  }
	
	public RuleReport run(Database database) {
		RuleReport ruleReport = new RuleReport(RULE_NAME, SUGGESTION, DEBT);
		
		for(Function f : database.getFunctions()) {
			Boolean isSuccess = assertion(f, database.getComments());
			ruleReport.addEntry(new ReportEntry(f.getEntityDescription(), isSuccess));
		}
		
		return ruleReport;
	}
	
	private Boolean assertion(Function function, List<Comment> comments) {
		for(Comment c : comments) {
			if(c.getEntityType().equals(Comment.FUNCTION_TYPE)
					&& c.getSchemaName().equals(function.getSchemaName())
					&& c.getEntityName().equals(function.getName())) {
				return true;
			}
		}
		
		return false;
	}
}