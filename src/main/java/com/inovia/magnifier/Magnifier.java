package com.inovia.magnifier;

import databaseObjects.Table;


public class Magnifier {
	public static void main(String[] args) {
		Configuration conf = null;
		Database database = null;
		try {
			conf = new Configuration(args);
			database = new Database(conf);

			for(Table t : database.getTables()) {
				System.out.println(t);
			}

			System.out.println("Alright");
		} catch(UnsupportedOperationException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			database.disconnect();
		}
	}
}
