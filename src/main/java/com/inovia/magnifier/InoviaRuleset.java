package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rules.*;

/**
 * The InoviaRuleset class deals with checking
 * Inovia specific rules over a database
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
