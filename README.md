## Magnifier

Magnifier is a database analyzer able to perform convention validations. Its goal is to allow the developers to check the consistency of their database over a set of configurable rules. 

## Development

To develop on Magnifier you will need:
1. [Maven](https://github.com/apache/maven).
2. The JDBC Driver for your DBMS.

## Deployment

1. Retrieve the magnifier.jar file
2. Run Magnifier

Usage

Usage: OptionsTip
 -d <arg>    The database name
 -dp <arg>   The JDBC driver package
 -h <arg>    The database host
 -o <arg>    The report output path/name
 -p <arg>    The database listening port
 -pw <arg>   The password
 -t <arg>    DBMS
 -u <arg>    The username

Example:
java -jar magnifier.jar -h host -p 5432 -t postgresql -dp ./jdbc_pgsql.jar -d “myDatabase” -u john -p myPassword -o report.html


## Contributing

Feel free to contribute in the project. Fork us.

## Issues

Let us know if you've found a bug or have a new feature to suggest.

## License

Magnifier is released under the [MIT License](http://www.opensource.org/licenses/MIT).