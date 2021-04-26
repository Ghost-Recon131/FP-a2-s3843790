# About
Name: Jingxuan Feng

Student number: s3843790
### Main Class
It is located under src/main/Main.java

## Controllers
Contains all the controllers used in the program
#### 1. Login Controller
This controller is responsible for checking against the entered credentials against the database to ensure the account 
exists , and the login detail is valid. 
#### 2. RegisterController
This controller is responsible for employees to register an account and adding their account details into the SQLite
database. 

## DAO
These are Data access objects that perform retrieving, adding, updating or removing data from the database. 
1. AdminDAO: An extension to the EmployeeDAO, it allows for adding of admin accounts, editing almost all data in an 
   employee account.
   
2. EmployeeDAO: Provides getters for almost all data fields stored in SQLite table. Also provides method for signing up
to new accounts
   
3. SQLConnection: Provided by starter code

## Model
1. EmployeeModel: Provides constructor & getters used by DAO 
2. LoginModel: Provides login functionality
3. SHAHash: Provides hashing method and random number generation
## View

# References
1. [1]S. Akshif, "VoxelPixel/HashingAlgorithmsInJava", GitHub, 2021. [Online]. Available: 
   https://github.com/VoxelPixel/HashingAlgorithmsInJava/blob/master/SHA256.java. [Accessed: 25- Apr- 2021].