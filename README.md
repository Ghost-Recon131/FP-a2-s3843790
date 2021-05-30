# About
Name: Jingxuan Feng

Student number: s3843790
### Main Class
It is located under src/main/Main.java

## Assumptions Made for the assignment
1. Number of tables: Currently the number of tables is fixed and there is no way to add more tables without manual rework
   of the .fxml files, SQL database, DAO's as well as the corresponding controllers and models. 


2. Number of employees: Currently there is no limit on the number of employee accounts that can be created active in the
   system. However, there are checks in place to prevent the same person from creating multiple accounts if they use the
   same details and not lie about their name. Since it is a company program, it should not be hosted publicly online, so
   the risk of having trolls attack the system is minimised and limiting accounts seem unnecessary. Currently, it will
   be the admins' responsibility to manage employee accounts. 
   

3. Automatic partial lockdown: It is assumed that in the event of 50% capacity requirement from the government, the 
   company will want to maximise the number of Employees that can sit at the office. Given this case, either every odd
   or even table will be locked down automatically to preserve the maximum of 5 tables available. 
   
   If we allowed employees to pick amongst themselves, it could result in the following: 

   | Table 1                | Table 2   | Table 3               | Table 4    | Table 5               |
   | :--------------------: | :-------: | :-------------------: |:---------: |:---------------------:|
   | Locked due to spacing  | Reserved  | Locked due to spacing | Reserved   | Locked due to spacing |

   This will result in lost capacity for the company. Hence, the current implementation is more likely to be chosen by a 
   company.
   

4. Operating System: This program has been written in a Windows 10 environment and has not been validated to work for any
   other operating systems. As a result, running this program in another OS may result in features not working due to
   difference in folder structure or format. It is assumed that the user of this program will also be running Windows. 
   
## Controllers - Shared
Contains all the controllers used in the program

1. HomeScreenController: Responsible for switching to different scenes ie login, register ect. 

2. Login Controller:This controller is responsible for checking against the entered credentials against the database to 
   ensure the account exists , and the login detail is valid. 

3. RegisterController: This controller is responsible for employees to register an account and adding their account 
   details into the SQLite database. 
   
4. ResetPasswordController: Matches a user's inputs against the database then updates their password if the provided 
   inputs are correct. It will then show the new password to the user. 
   
## Controllers - Admin
1. AddUpdateAccountsController: Handles updating or creating new accounts

2. AdminHomeController: Displays the home menu for an admin user. Also provides a link to all other admin functionalities.

3. COVIDRestrictionsController: Allows admins to set restriction levels at a company-wide or per table level. 

4. GenerateCSVReportController: Admin can output the contents of the SQL database into a CSV file.

5. ManageAccountsController: Allows admins to change information on an Admin account, add, delete or set an
   account as inactive.

6. ManageViewBookingsController: Admins can view bookings on a particular date and manage pending / approved bookings. 

## Controllers - User
1. BookTableController: Allows users to select a date and table to reserve. Cancelling bookings is also done here.

2. ChangePasswordController: Checks the user's password matches then updates the database with their new password.

3. UserHomeController: Responsible for switching to different scenes and showing the employee any updates on COVID
   lockdown, or their reservation request.

## Utils
These are supporting classes which provides methods that may be used by controllers or DAOs'
1. ChangeSceneUtil: Allows for changing scenes, used by controllers inside AdminFeatures and UserFeatures. 

2. DateFormatConversionUtil: Handles converting Strings into LocalDate as well as handling exceptions. 

3. IntegerCheckUtil: Checks the provided integer & handles exceptions if the value provided is not an integer

4. RandPasswordUtil: Generates a new password when a user or admin uses the "Forgot Password" feature

5. RandValueUtil: Provides random number generation for generating employee ID & Booking ID

6. SHAHashUtil: Provides hashing method and returns hash in the form of a string

7. StringCheckUtil: Provides 2 methods. VerifyString will return false if a string is empty and true if it is not. The other
method; InputNotEmpty checks for GUI text fields and ensures the user has entered something. It will return true if
   nothing is entered, as well as setting a warning message. 
   
8. TableStatusUtil: Originally created due to different scenes being used to show user booking / cancel booking. This is 
   now combined into a single scene. 
   
   This still provides useful features such as setting the colour of the tables based on their status. 
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

3. LoginModel: Provides helping methods to LoginController. Also allows other classes to get the current user's role and ID.

4. RegisterModel: Provides additional SQL queries and supporting methods to RegisterController

5. ResetPasswordModel: Provides additional SQL queries and supporting methods to ResetPasswordController

6. TableModel: Provides constructor & getters used by TableDAO

## Model - AdminModel
1. AddUpdateAccountsModel: Provides supporting methods for AddUpdateAccountsController.

2. AdminHomeModel: Provides supporting methods for AdminHomeController.

3. COVIDRestrictionsModel: Provides supporting methods for COVIDRestrictionsController.

4. GenerateCSVReportModel: Provides supporting methods for GenerateCSVReportController.

5. ManageAccountsModel: Provides supporting methods for ManageAccountsController

6. ManageViewBookingsModel: Provides supporting methods for ManageViewBookingsController.

## Model - UserModel
1. BookTableModel: Provides supporting methods for BookTableController
   
2. ChangePasswordModel: Provides supporting methods for ChangePasswordController

3. UserHomeModel: Provides supporting methods for UserHomeController

## View - Shared
1. HomeScreen.fxml: Default home page when starting the program

2. Login.fxml: GUI for login

3. Register.fxml: GUI for registering a new employee account

4. ResetPassword.fxml: Provides GUI for resetting password. Can be used by both admins and employees. Also Shows the new 
   password after a password reset.
## View - Admin
1. AddUpdateAccounts.fxml: GUI for adding or updating employee and admin accounts

2. AdminHome.fxml: Homepage for admin accounts & provides connection to other features

3. COVIDRestrictions.fxml: GUI for managing COVID restrictions

4. GenerateCSVReport.fxml: GUI for exporting CSV file

5. ManageAccounts.fxml: GUI for adding / deleting accounts. Provides connection to AddUpdateAccounts.fxml

6. ViewBookings.fxmlL GUI for admin to see bookings on a particular date + who reserved the table, and their booking ID
Also allows for managing bookings here

## View - User
1. BookTable.fxml: GUI for employees to reserve a table

2. ChangePassword.fxml: GUI for employees to change their password

3. UserHome.fxml: The default home page for employee accounts once they have logged in

## SQlite table structure
1. Bookings SQlite Table: Stores information about Bookings made by employee or admin

2. Employee SQlite Table: Stores information about an employee

3. sqlite_master SQlite Table: Was part of the starter code and cannot be deleted for some reason. Maybe it
   has configuration settings? 

4. Tables SQlite Table: Stores status of Tables such as whether it is available or under lockdown.

## Registered Testing accounts
There are a number of accounts already registered in the system used for testing purposes. All employee accounts can be
deleted but make sure the corresponding entries in Bookings table are also deleted. All admin accounts can also be deleted, 
but I highly suggest that 1 admin account is saved, otherwise there will be no way to add admin accounts back in the program
without using an admin account. The only way to recover admin functionality is to manually add an admin account in the 
Employee SQL table. If you would like to use the already existing accounts, here are the credentials: 

### Admin Accounts
   | Username      | Password              |
   | :-----------: | :-------------------: |
   | admin         | admin                 |
   | admin2        | admin2                |

### User Accounts
   | Username      | Password              |
   | :-----------: | :-------------------: |
   | Tom1          | Tom1                  |
   | Test1         | Test1                 |
   | Test2         | Test2                 |
   | DickSmith     | DickSmith             |
# References
1. [1]S. Akshif, "VoxelPixel/HashingAlgorithmsInJava", GitHub, 2021. [Online]. Available: 
   https://github.com/VoxelPixel/HashingAlgorithmsInJava/blob/master/SHA256.java. [Accessed: 25- Apr- 2021].

   