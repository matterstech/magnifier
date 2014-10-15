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

			System.out.println("Triggers");
			for(Trigger t : database.getTriggers()) {
				System.out.println(t);
			}
			
			System.out.println("Foreign Keys");
			for(ForeignKey fk : database.getForeignKeys()) {
				System.out.println(fk);
			}
			System.out.println("PrimaryKeys");
			for(PrimaryKey pk : database.getPrimaryKeys()) {
				System.out.println(pk);
			}
			System.out.println("Uniques");
			for(Unique u : database.getUniques()) {
				System.out.println(u);
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
