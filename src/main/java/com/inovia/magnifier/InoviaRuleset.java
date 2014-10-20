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
		
		rules.add(new ForeignKeyName(this));
		rules.add(new FunctionHasComment(this));
		rules.add(new FunctionParameterName(this));
		rules.add(new IndexName(this));
		rules.add(new TableHasComment(this));
		rules.add(new TableHasPrimaryKey(this));
		rules.add(new TriggerHasComment(this));
		rules.add(new TriggerName(this));
		rules.add(new ViewHasComment(this));
		rules.add(new ViewName(this));
		
		return rules;
	}
}
