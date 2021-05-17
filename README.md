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

## Utils
These are supporting classes which provides methods that may be used by controllers or DAOs'
1. RandValueUtil: Provides hashing method and
   
2. SHAHashUtil: Provides random number generation for generating employee ID & Booking ID

## DAO
These are Data access objects that perform retrieving, adding, updating or removing data from the database.

1. BookingsDAO: Getters and setters which will be used to access Bookings SQL table. Also provides setters that admins 
   use, they require current user to be an admin.

2. EmployeeDAO: Provides getters for almost all data fields stored in SQLite table. Also provides method for signing up
to new accounts. Also provides setters that admins use, they require current user to be an admin. 
   
3. SQLConnection: Provided by starter code. 

4. TableDAO: Getters and setters which will be used to access Tables SQL table. Also provides setters that admins use, 
   they require current user to be an admin.

## Model
1. BookingsModel: Provides constructor & getters used by BookingsDAO
2. EmployeeModel: Provides constructor & getters used by EmployeeDAO 
3. LoginModel: Provides login functionality
4. TableModel: Provides constructor & getters used by TableDAO
## View
1. Login.fxml: GUI for login

##SQlite table structure
1. Bookings SQlite Table: Stores information about Bookings made by employee or admin
2. Employee SQlite Table: Stores information about an employee
3. sqlite_master SQlite Table: Was part of the starter code and cannot be deleted for some reason. Maybe it
   has configuration settings? 
4. Tables SQlite Table: Stores status of Tables such as whether it is available or under lockdown.
# References
1. [1]S. Akshif, "VoxelPixel/HashingAlgorithmsInJava", GitHub, 2021. [Online]. Available: 
   https://github.com/VoxelPixel/HashingAlgorithmsInJava/blob/master/SHA256.java. [Accessed: 25- Apr- 2021].

   