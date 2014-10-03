package databaseObjects;

public abstract class DatabaseObject {
	protected String name;
	
	public DatabaseObject(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
}
