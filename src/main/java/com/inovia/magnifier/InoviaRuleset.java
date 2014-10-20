package com.inovia.magnifier;

import java.util.*;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;
import com.inovia.magnifier.rules.*;

public class InoviaRuleset extends Ruleset {
	public InoviaRuleset(Database database) {
		super(database);
		
		rules = new Vector<Rule>();
		if(database != null) {
			report = new Report(database.getName());
		}
	}
	
	protected List<Rule> getRules() {
		List<Rule> rules = new Vector<Rule>();
		
		rules.add(new ForeignKeyName(database));
		rules.add(new FunctionHasComment(database));
		rules.add(new FunctionParameterName(database));
		rules.add(new IndexName(database));
		rules.add(new TableHasComment(database));
		rules.add(new TableHasPrimaryKey(database));
		rules.add(new TriggerHasComment(database));
		rules.add(new TriggerName(database));
		rules.add(new ViewHasComment(database));
		rules.add(new ViewName(database));
		
		return rules;
	}
}
