package com.inovia.magnifier;

import com.inovia.magnifier.databaseObjects.*;


public class Magnifier {
	public static void main(String[] args) {
		Configuration conf = null;
		Database database = null;
		try { // This is for testing
			
			conf = new Configuration(args);
			database = new Database(conf);

			System.out.println("Functions");
			for(Function f : database.getFunctions()) {
				System.out.println(f);
			}
			System.out.println();

			System.out.println("Alright");
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
