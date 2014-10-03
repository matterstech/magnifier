package databaseObjects;

public abstract class DatabaseObject {
	protected String name;
	
	public DatabaseObject(String p_name) {
		name = p_name;
	}
	
	public String toString() {
		return name;
	}
}
