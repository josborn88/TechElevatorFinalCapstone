# Final Capstone For Tech Elevator

This is my final project from Tech Elvator's coding boot camp. Tech Elevator is a 14 week program where you learn the basics of programmingand software development. 

The final capstone projects are meant to give students experince working on a more involved project using an agile workflow. Towards that end it is designed to take two weeks for a team of four studnets to complete. We worked in two day sprints with one of the instructors serving as Srim Master and anotheras product owner.

To make the project meaningful and unique to each each teams can pick from projects that are sourced from students, local businesses, or based on tools tech Elevator instructors would like for their work. 

The project I selected was for a local security consulting company who wanted to improve the way they reported and tracked issues for their clients. Previously they used a combination of paper and Google Docs to keep everything straight and the owner was hoping for a web application that could handle taking a report and displaying reports based of different criteria such as issue priority or location.


## The core functions of the application are:

Support the existince of multiple user roles and change 

Allow a user to report a risk they see. The report should include a picture of the issue, a description, a location, as well as the name of the user making the report.

A user must be able to view reports and filter them down based on issue priority (non assigned, low, medium, high), repair status, location, or client.

A user must be able to change report status such as priority or repair status if their role is allowed to do so.

All users must be logged in to do anything and must have the ability to change their password.

## Technologies Used in the Project

This project runs on a Vue.js frontend and uses a RESTful API built in Java for it's backend. The data is stored in a PostgreSql database connected to Java via JDBC.

We used JUnit extensivly for unit testing and followed TDD principles failty closely.

## My Role in the Project

I played the part of a full stack developer in this project and was able to help where needed. I spent a lot of time working to get the image uploading to work correctly, I worked on getting Vue to display the images once the reports were loaded from the API, I helped work out some of the kinks with the JDBC class that pulled reports from the database, and I spent a good bit of time getting vue to use the API correctly. 

 I also made a mock API to use with Vue until the Java API was finished.

## Running The Application

To run this application you will need to start the Java backend on a Tomcat server running on port 8080. 
To run the front end: run the Vue project on an Node server and use your browser of choice t ovisit the localhost post your node server is running on.
