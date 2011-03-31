- Add the dependency to the POM
- If there is a configuration file then
	- Add the configuration file with placeholders in resources
	- At installation time, unpack the dependency and parse the configuration file to add configuration values
	- Pack the folder unpacked on the previous step
	- Deploy the dependency depending on its nature (WAR, JAR, ...)
	
* TODO : Validate fields on panels
http://izpack.org/documentation/user-input.html#validating-field-content

Or create our own validators (for example, check a JDBC connection, a Web Service, ...)

TODO : 
1. Choose local or remote Sesame instance
2. HTML summary file opens in browser
3. HTTP Proxy information


* Process Editor : Parameters in userInputSpec
* All : Will be available
* Summary before install
* Launch Web page at end?
* Create Repos in Sesame after installation but before start?
* External properties?