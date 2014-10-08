package com.inovia.magnifier;

import com.inovia.magnifier.database.Database;
import com.inovia.magnifier.database.DatabaseFactory;

public class Magnifier {
	public static void main(String[] args) {
		Configuration configuration = null;
		Database database = null;
		try {
			configuration = new Configuration(args);
			DatabaseFactory.getDatabase(configuration);
			
			
		} catch(UnsupportedOperationException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if(database != null) {
				database.disconnect();
			}
		}
	}
}
