## Magnifier

Magnifier is a database analyzer able to perform convention validations. Its goal is to allow the developers to check the consistency of their database over a set of configurable rules. 

## Development

To develop on Magnifier you need:

- [Maven](https://github.com/apache/maven).
- The JDBC Driver for your DBMS.

## Deployment

- Retrieve the magnifier.jar file
- Download the JDBC driver for your database
- Run Magnifier

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


## Contributing

Feel free to contribute in the project. Fork us.

## Issues

Let us know if you've found a bug or have a new feature to suggest.

## License

Magnifier is released under the [MIT License](http://www.opensource.org/licenses/MIT).