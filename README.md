# About
Name: Jingxuan Feng

Student number: s3843790
### Main Class
It is located under src/main/Main.java

## Controllers - Shared
Contains all the controllers used in the program

1. HomeScreenController: Responsible for switching to different scenes ie login, register ect. 

2. Login Controller:This controller is responsible for checking against the entered credentials against the database to 
   ensure the account exists , and the login detail is valid. 

3. RegisterController: This controller is responsible for employees to register an account and adding their account 
   details into the SQLite database. 
   
4. ResetPasswordController: Matches a user's inputs against the database then updates their password if the provided 
   inputs are correct. Then passes new password to ResetPasswordController2.

5. ResetPasswordController2: Displays the new password to the user.
## Controllers - Admin
1. AdminHomeController: Not yet implemented

## Controllers - User
1. BookTableController: 

2. ChangePasswordController: Checks the user's password matches then updates the database with their new password.

3. UpdateBookingController: Responsible for updating an employee's existing booking as well as handle any errors.

4. UserHomeController: Responsible for switching to different scenes and showing the employee any updates on COVID
   lockdown, or their reservation request.

## Utils
These are supporting classes which provides methods that may be used by controllers or DAOs'
1. IntegerCheck: Checks the provided integer & handles exceptions if the value provided is not an integer

2. RandPasswordUtil: Generates a new password when a user or admin uses the "Forgot Password" feature

3. RandValueUtil: Provides random number generation for generating employee ID & Booking ID

4. SHAHashUtil: Provides hashing method and returns hash in the form of a string

5. StringCheck: Provides 2 methods. VerifyString will return false if a string is empty and true if it is not. The other
method; InputNotEmpty checks for GUI text fields and ensures the user has entered something. It will return true if
   nothing is entered, as well as setting a warning message. 
## DAO
These are Data access objects that perform retrieving, adding, updating or removing data from the database.

1. BookingsDAO: Getters and setters which will be used to access Bookings SQL table. Also provides setters that admins 
   use, they require current user to be an admin.

2. EmployeeDAO: Provides getters for almost all data fields stored in SQLite table. Also provides method for signing up
to new accounts. Also provides setters that admins use, they require current user to be an admin. 
   
3. SQLConnection: Provided by starter code. 

4. TableDAO: Getters and setters which will be used to access Tables SQL table. Also provides setters that admins use, 
   they require current user to be an admin.

## Model - Overall
1. BookingsModel: Provides constructor & getters used by BookingsDAO

2. EmployeeModel: Provides constructor & getters used by EmployeeDAO 

3. LoginModel: Provides login functionality

4. RegisterModel: Provides additional SQL queries and supporting methods to RegisterController

5. ResetPasswordModel: Provides additional SQL queries and supporting methods to ResetPasswordController

6. TableModel: Provides constructor & getters used by TableDAO

## Model - AdminModel
1. AdminHomeModel: Provides supporting methods for AdminHomeController

## Model - Overall
1. UserHomeModel: Provides supporting methods for UserHomeController

## View - Shared
1. HomeScreen.fxml: Default home page when starting the program

2. Login.fxml: GUI for login

3. Register.fxml: GUI for registering a new employee account

4. ResetPassword.fxml: Provides GUI for resetting password. Can be used by both admins and employees.

5. ResetPassword2.fxml: Shows the new password after a password reset. 

## View - Admin
1. Login.fxml: GUI for login

## View - User
1. BookTable.fxml: GUI for employees to reserve a table

2. ChangePassword.fxml: GUI for employees to change their password

3. UpdateBooking.fxml: Essentially the same as BookTable but updated with the user's booking

4. UserHome.fxml: The default home page for employee accounts once they have logged in

## SQlite table structure
1. Bookings SQlite Table: Stores information about Bookings made by employee or admin

2. Employee SQlite Table: Stores information about an employee

3. sqlite_master SQlite Table: Was part of the starter code and cannot be deleted for some reason. Maybe it
   has configuration settings? 

4. Tables SQlite Table: Stores status of Tables such as whether it is available or under lockdown.

# References
1. [1]S. Akshif, "VoxelPixel/HashingAlgorithmsInJava", GitHub, 2021. [Online]. Available: 
   https://github.com/VoxelPixel/HashingAlgorithmsInJava/blob/master/SHA256.java. [Accessed: 25- Apr- 2021].

   