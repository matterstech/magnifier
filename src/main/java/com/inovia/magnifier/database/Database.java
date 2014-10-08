package com.inovia.magnifier.database;

import java.sql.*;


public interface Database {
	public Connection connect();
	public void disconnect();
}
