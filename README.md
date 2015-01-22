## Magnifier

Magnifier is a database analyzer able to perform convention validations. Its goal is to allow the developers to check the consistency of their database over a set of configurable rules.

## Supported DBMS

* PostgreSql

## Build

To build and run Magnifier you need:

* Recommended: Java JDK 1.6+
* [Maven](https://github.com/apache/maven).
* The JDBC Driver for your DBMS.

Build procedure:
    
    $> git clone https://github.com/inovia-team/magnifier.git
    $> cd magnifier && mvn package

Magnifier is ready to be executed

    $> java -jar target/magnifier-0.0.1-SNAPSHOT-jar-with-dependencies.jar --help

## Deployment

* Install Java JRE 1.6+
* Retrieve the magnifier.jar file
* Download the JDBC driver for your database ([postgresql](https://jdbc.postgresql.org/download.html))
* Run Magnifier

## Usage

	Usage: OptionsTip
	-d <arg>    The database name
	-dp <arg>   The JDBC driver package
	-h <arg>    The database host, default is localhost
	-o <arg>    The report output path/name, default is ./report.html
	-p <arg>    The database listening port, default is the specified DBMS default port
	-pw <arg>   The password
	-t <arg>    DBMS
	-u <arg>    The username

## Examples

Analyze the local "magnifier__dev" postgresql database with username "john" and password "john_password"

	java -jar magnifier.jar -t postgresql -dp ./jdbc_pgsql.jar -d magnifier_dev -u john -pw john__password

Analyze the distant "magnifier__dev" postgresql database, listening on port 5432, with username "john" and password "john_password"

	java -jar magnifier.jar -h 192.168.0.15 -p 5432 -t postgresql -dp ./jdbc_pgsql.jar -d my__database -u john -pw john_password -o report.html

## Analysis
	Rule                  | Checkings                                                    | Example
	----------------------+--------------------------------------------------------------+---------------
	TableHasPrimaryKey    | tables have a primary key                                    | 
	TableHasComment       | tables have a comment                                        | 
	ViewHasComment        | views have a comment                                         | 
	TriggerHasComment     | triggers have a comment                                      | 
	FunctionHasComment    | functions have a comment                                     | 
	TriggerName           | triggers have name matching "on_$when_$action_$table"        | on_before_update_user
	ViewName              | views have name ending with "_view"                          | user_view
	IndexName             | indexes have name ending with "_pkey" or "_idx"              | user_idx
	ForeignKeyName        | FKs have column name composed of referenced table and column | user_id
	FunctionParameterName | function parameter names ending with "_in" or "_out"         | add(val1_in, val2_in, res_out)

	

## Contributing

Feel free to contribute in the project. Fork us.

## Issues

Let us know if you've found a bug or have a new feature to suggest.

## License

Magnifier is released under the [MIT License](http://www.opensource.org/licenses/MIT).