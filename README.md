# Java Final Capstone For Tech Elevator

This is my final project from Tech Elvator's coding boot camp. 

The application was developed for Fidelis NA, a local security consulting company, who wanted to improve the way they reported and tracked security risks for their clients. Fidelis required the application to support multiple user roles and to restrict functionality based on those roles.  

The core functions of the application are:

Allow a user to report a risk they see. The report should include a picture of the issue, a description, a location, as well as the name of the user making the report.

A user on the maintanence team must be able to view reports that have been made and assign priority levels to them. 

A user on the supervisor team must be able to view reports and to filter them by priority, repair status, and location. These filters are stackable, so a user can look for all high priority issues that are not repaired at a certain location for instance.

A supervisor must also be able to update the repair status of a report or mark the report as a duplicate of a previous report. When marking an issue repaired a user is prompted for the name of the person verifying the issue is repaired.

A system admin user must be able to create new users and assign them roles.

All users must have to be logged in to use the application, and users must be able to change their password.

## Technologies Used in the Project

This project runs on a Vue.js frontend and uses a REST API that runs on a Java backend server. Data is stored in a PostgreSql database.

## My Role

I played the part of a full stack developer in this project and was able to help where needed. I spent a lot of time working to get the image uploading to work correctly, I worked on getting Vue to display the images once the reports were loaded from the API, I helped work out some of the kinks with the JDBC class that pulled reports from the database, and I spent a good bit of time getting vue to use the API correctly. 

To help avoid any bottlenecks caused by the lack of a fully functional API I also made a mock API for the front end team to use while the backend team was working. 

## Running

To run this application you will need to start the Java backend on a Tomcat server running on port 8080. 
To run the front end run the Vue project on an Node server and connect to the port it says to.
