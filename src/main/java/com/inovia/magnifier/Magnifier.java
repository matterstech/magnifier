package com.inovia.magnifier;

import java.sql.*;

import com.inovia.magnifier.Configuration;


public class Magnifier {
	public static void main(String[] args) {
		Configuration conf = null;
		Database database = null;
		try {
			conf = new Configuration(args);
			database = new Database(conf);

			Statement statement;
			try {
				statement = database.getConnection().createStatement();
				ResultSet results = statement.executeQuery("SELECT id, nickname FROM person");

				while(results.next()){
					//Retrieve by column name
					int id  = results.getInt("id");
					String nickname = results.getString("nickname");

					//Display values
					System.out.print("ID: " + id);
					System.out.println(", Nickname: " + nickname);
				}

				results.close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
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
