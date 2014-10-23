package com.inovia.magnifier.rule;

import java.util.List;
import java.util.Vector;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.reports.Report;

/**
 * it checks if Inovia's convention is followed on a database
 */
public class InoviaRuleset extends Ruleset {
	public InoviaRuleset(Database database) {
		super(database);
		
		rules = new Vector<Rule>();
		report = new Report(database.getName());
	}
	
	protected List<Rule> getRules() {
		List<Rule> rules = new Vector<Rule>();
		
		rules.add(new ForeignKeyName());
		rules.add(new FunctionHasComment());
		rules.add(new FunctionParameterName());
		rules.add(new IndexName());
		rules.add(new TableHasComment());
		rules.add(new TableHasPrimaryKey());
		rules.add(new TriggerHasComment());
		rules.add(new TriggerName());
		rules.add(new ViewHasComment());
		rules.add(new ViewName());
		
		return rules;
	}
}
