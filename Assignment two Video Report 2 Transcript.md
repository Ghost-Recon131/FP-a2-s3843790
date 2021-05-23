# Assignment Two Video Report 2 transcript
##Part 1 - Introduction
My name is Jingxuan Feng, and my student number is s3843790. This is my Assignment Two Video Report 1.

### Overview
I have fully implemented the home page, registration process, updated the login page, forget password feature and all
features required for an Employee account. 

## Part 2 - Updated Design and Database
The app has mostly followed the wireframe design. However, some changes were made, the 'Notifications area' has been kept but
not as much as shown in the wireframes. This is to save time and implement features that are actually required by the 
assignment specs. If I have extra time, then I will go back and implement this side feature. The calendar date selection
for choosing a booking date is also slightly different JavaFX not having the same calendar view as the wireframe design.

The database has also been updated. I moved Previous Table from Bookings table into Employees table, allowing getting this
value using the employee's ID rather than locating their previous booking ID then locating their previous table. 

TableStatus column in the Tables table is also depreciated and will be removed sometime in the future.

## Part 3 - Features Implemented through the code
In addition to what I have previously mentioned, I have implemented error checking into every feature that requires user
input. Such as checking all inputs are filled, passwords match ect. The booking feature also includes checking that a user
does not have more than 1 booking, the user books a table in the future, the user cannot cancel a non-existent booking or
cancel with less than 2 days till the reserved date, the user cannot book an already reserved table or table under COVID-19
lockdown and finally, the user cannot sit at the same table they sat at last time. 

## Part 4 - Demo and Lessons Learnt
I learnt that coding with a GUI requires a lot more patients and testing than a command line app. I have had to use 
significantly more time to implement features compared to the last assignment. 

I also learnt that more forward planning is required, and I need to spend more time planning before jumping straight into
coding the features. 

I've noticed a bug in Reset Password feature, and I will fix it after this video report. 