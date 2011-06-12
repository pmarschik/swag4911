Instructions:

- both projects (swag49 and swag.assignment2.model) have to be located in the same root folder.
- import swag.assignment2.model into eclipse
- to generate the model and the dao code:
	* open the swag.assignment2.model project
	* and run the generator.mwe file which is located in the workflow package
	* the code will be generated in the swag49 project: 
		- user-model classes and user-daos in swag49/swag49.model/swag49.model.user
		- map-model classes and map-daos in swag49/swag49.model/swag49.model.map
		- NOTICE: Please keep in mind that only the model and daos get generated. Everything else like entityListeners and helper or util classes have to stay in the respective folders.