
# PDF Generator System

### Hackathon project by Safeer Khan and Madhul Pandey

## Prerequesites

Need Google Chrome browser installed on system. On Windows, you need to add it to `PATH` as Environment Variable.

For calling Database operations, MySQL data base with the same schema will be requried. You will need to specify your user and password in `application.properties` file in res folder.

We have also attached the export of our database in sql folder. `export.sql`.

## Project overview

### Package Structure

There is one main package with two sub-pakages. 

One sub-package is `db`, which consists of all necessary files to implement the datamodel and handle database interactions as per Java Persistance Library(JPA).

The second sub-package is `Language`, which is used to hard code Strings in different languages. We make use of java inheritance to we can switch between languages quickly.

### Program overview.

We make use of Spring boot to expose a REST endpoint where we make requests. This code is present in `Controller.java.`

To run the program, you have to call main function from  `HackathonApplication.java`

The main logic code is present and documented in `Pdf.java`. Kindly change the hard-coded output directories as per your system. Check TODO comments for the same.