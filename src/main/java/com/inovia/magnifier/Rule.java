package com.inovia.magnifier;

import com.inovia.magnifier.database.*;
import com.inovia.magnifier.reports.*;

public abstract class Rule {
	protected Database database;
	
	public Rule(Database database) {
		this.database = database;
	}
	
	abstract public RuleReport run();
}
