package databaseObjects;


public class Index extends DatabaseObject {
	private String tableName;
	
	public Index(String name, String tableName) {
		super(name);
		this.tableName = tableName;
	}
	
	public String getTableName() {
		return tableName;
	}
}
