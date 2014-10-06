package com.inovia.magnifier;

import com.inovia.magnifier.databaseObjects.*;

/**
 * 
 * @author joeyrogues
 *
 */
public class Magnifier {
	public static void main(String[] args) {
		Configuration conf = null;
		Database database = null;
		try { // This is for testing

			conf = new Configuration(args);
			database = new Database(conf);

			System.out.println("Tables");
			for(Table t : database.getTables()) {
				System.out.println(t);
			}
			System.out.println();

			System.out.println("Indexes");
			for(Index i : database.getIndexes()) {
				System.out.println(i);
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
