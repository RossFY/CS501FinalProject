# This is the Introduction of the Final Project

#Method: Java + MySQL

This project is about Student Grade Management System. It allows users to add, delete, modify and search the information which is stored in database.

#There are 6 java files in this project:

#Main.java:
This is the Main Program file, users should run the program on this page.

#JDBC_Connection.java:
This page is used for connecting to MySQL server, you could change the username and password on this page. (By the way, in order to run on your computer, you should not only change the username and password but also create a table named 'student', you could use the following query.)</br>

CREATE TABLE Student (</br>
    stu_id CHAR(10) PRIMARY KEY,</br>
    stu_name CHAR(20) NOT NULL,</br>
    Java_score DOUBLE(5,2),</br>
    Python_score DOUBLE(5,2),</br>
    C_score DOUBLE(5,2),</br>
    Total_score DOUBLE(5,2)</br>
);</br>

#JDBC_Close.java:
This page is used for disconnecting from MySQL server.

#SGMS.java:
This page is the main page of the program.

#Operations.java
This page contains of add(), show(), modify(), delete() and search() functions.

#checkFunctions.java
This page is all the check valid functions. In order to check each input is valid or not.</br>

I think the program is easy for you to run, because every steps have been explained clearly.
