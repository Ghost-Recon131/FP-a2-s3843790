# Refactoring Report
Over the course of implementing this program, a few changes has been made to the GUI design. Most of these changes take
place for the Admin functionalities.

1. Changed Admin Home Page
   The buttons shown has been reduced and renamed to reflect changes in submenus.


2. Adding / Updating user and admin accounts
   This feature received the largest change compared to the initial Wireframe designs. Adding and updating user and admin
   accounts are now combined into a single page as opposed to going through a series of submenus and pages in the design
   based off the Wireframe. This reduces a lot of code, simplifies the program and increases usability.


3. Managing COVID restrictions
   Slight redesign of the GUI + added option to lockdown or release a specific table.


4. Generate CSV Report
   The GUI is changes and now requires the user to input a output path for the CSV report.


5. Manage Accounts
   This feature was changed so that an admin can manage both other Admin accounts and employee accounts from the same
   page. This is more user-friendly than having to go to a different page to view a list of Admin or User accounts,
   memorise / copy the account ID then come back to


6. Viewing and Managing Bookings
   These were originally designed to have their own pages but are now combined into page. This allows for better usability
   rather than view the bookings on one page, then approve or deny them on another.

   Originally, I planned for Admins to be able to alter booking details but that requires a lot of extra error checking
   and validating bookings so this feature was removed.


7. Notifications Box
   This was a planned extra feature which I did not implement due to more time was needed to be allocated to other my
   other subjects. This currently has no functionality and is more of a decoration & fills the otherwise empty space. 