Instructions:

- both projects (swag.assignment2.demo and swag.assignment2.model) have to be located in the same root folder.
- import both projects in eclipse
- to generate the model, dao and persistence code:
	* open the swag.assignment2.model project
	* and run the generator.mwe file which is located in the workflow package
	* the code will be generated in the swag.assigment2.demo project: 
		- model classes in the swag.model package
		- dao classes in swag.dao package
		- the persistence helper class in the swag.util package 
		- and the hibernate persistence.xml file in META-INF folder.
- to run the unit-tests for the daos
	* you can either execute the ant run task
	* or open the swag.assigment2.demo project and run the SWAGTestSuite.java class in the test/swag folder