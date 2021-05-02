# Assignment Two Video Report 1 transcript
##Part 1 - Introduction 
My name is Jingxuan Feng, and my student number is s3843790. The purpose of this recording is for Milestone 2 of Further Programming in semester 1 2021.
## Part 2 - Admin UI Wireframes 
I have created wireframes for all functional requirements for an admin user. Most wireframes begin with the admin already signed in and at their menu screen:
- A settings page for the admin to change COVID-19 restriction levels 
- Option for managing user bookings
- A series of pages and options for managing employee accounts
- Very similar options for managing admin accounts
- Option for admin to generate CSV reports
- Finally, a way for admins to check bookings on a specific date
## Part 3 - Employee UI Wireframes
I've also implemented all features for the employee accounts. The wireframes I created will demonstrate: 
- An employee logging in or registering for an account
- An employee who needs to reset a forgotten password
- Options for employee to book a table, update their reservation, change their account password, remove their reservation 
  and check in to their table on the day. 
## Part 4 - SQLite Tables created
I have created 3 SQL tables for storing data for the program to use. 
1. Bookings SQL Table: Stores information about Bookings made by employee or admin
2. Employee SQL Table: Stores information about employee and admin accounts. Password and answer to secret question 
   is hashed using SHA-256 before being stored into the database. 
3. sqlite_master SQL Table: Was part of the starter code and will not allow me to delete it
4. Tables SQL Table: Stores status of Tables such as whether it is available or under lockdown.
## Part 5 - Code status
I have made a start on the project's backend code such as DAO's and models for each of the tables, reorganised the starter 
code structure. I also added two supporting classes under the package 'utils' as they provide supporting methods for other 
classes such as generating random numbers, and the hashed result of an input. 