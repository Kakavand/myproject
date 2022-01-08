#Full implementation of a Web Poller

Written by Shahow kakavandy 2022.

This project wanted me to to build a simple web service in the form of a poller. To do this I was allowed to use whatever methods or stack I thought was feasible. However there were some constraints and requirements I had to meet in order to succeed. Below you will find the instructions;

Technologies I used: Java, Spring Boot, Maven, Docker, MySQL, Postman and ReactJS.

As a part of scaling the number of services running within a modern tech company we need a way to make sure our systems are running smoothly. None of the monitoring tools that we have looked at satisfy our requirements so we have decided that we will build one ourselves. What we want you to do is to build a simple service poller that keeps a list of services (defined by a URL), and periodically performs a HTTP GET request to each and stores a record of the response ("OK" or "FAIL"). Apart from the polling logic we want to have all the services visualised and easily managed in a basic UI presenting all the services together with their status.

###Requirements;

Basic requirements: 
● A user needs to be able to add a new service with URL and a name 
● Added services have to be kept when the server is restarted
● Display the name, url, creation time and status for each service ● Provide a README in english with instructions on how to run the application

###Extra requirements:

● We want full create/update/delete functionality for services
● The results from the poller are automatically shown to the user (no need to reload the page to see results) 
● We want to have informative and nice looking animations on add/remove services 
● The service properly handles concurrent writes 
● Protect the poller from misbehaving services (for example answering really slowly) 
● URL Validation ("sdgf" is probably not a valid service) 
● Multi user support. Users should not see the services added by another user

###Constraints:

This task is intentionally open-ended with no “boilerplate” code provided. It’s up to you to implement the solution using frameworks that you deem suitable for this task. The solution should have a backend which the user accesses over some sort of network, and you should use Java as the base language for the backend solution. We also ask you to use an SQL-based database. Apart from that you’re free to use whatever suits you best in performing the task. Be prepared to be able to explain the reasoning behind why you choose a particular solution. If you want to keep with our tech stack, it’s based on vertx, rxjava, react and mysql and builds with gradle. If you decide to use MySQL as a database, we ask you to: ● Configure it to run on port 3309. ● With credentials:

○ User: dev ○ Password: secret

I provided a docker compose file to run the Mysql-database.

##How to run the application:

Make sure you have Java 11 installed, Docker, MySQL and ReactJS.

Run the Application from the root folder.

Make sure the you can run the database on docker and that you have linked to your IDE.

Make sure that you the database up and running, and maybe also create a table for the constructor in your mysql client.

You should be able to access the reactJS based UI at http://localhost:3000/services in your Web browser.

You can now use the whole functionality in the UI.

Things I could add and improve;

Do unit testing, and integration testing.
The following points were not implemented;
● Multi user support. Users should not see the services added by another user.

>>There are multiple ways to do this, and I think it could be interesting to look into for the future.

● Protect the poller from misbehaving services (for example answering really slowly)


