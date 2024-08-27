# Movie Reviewer
A Movie Reviewer which can sort movie reviews, register users, save reviews into a text file, read a text file, add/delete reviews, etc. I recommend you try it and share your reviews with me and everyone who can see my GitHub page.

![image](https://github.com/AhmedAbdulwasi/Movie-Reviewer/assets/98428365/68083ad0-519d-4e0e-bb16-b0b102564cda)


## Why?
To dive deeper into Java, specifically its classes, handling errors, I/Os, public methods, and the 4 pillars of OOP which are abstraction (interact without needing to know how it works), encapsulation (keep a class private and access it through public methods), Inheritance (inheriting existing classes), and polymorphism (use the same function for different meaning). 

Additionally, there was an Intro to Java course that I had to take this winter semester (CPS209) which explains why I haven't been active in Java for 3 months. Now I want to build upon that knowledge and take a proactive approach to my learning. Also, I had this idea of creating a program relating to movies for at least a year and never got to plan it since I've always been confused as to how it should be implemented. This project took about a week to finish. Enjoy!

**This project also leveraged the 4 pillars of OOP:**
- Encapsulation: The User, Review and MovieReviewsManager uses classes to encapsulate data and the methods to operate on that data.
- Inheritance: The AdminUser extends the User class, inheriting its properties and methods.
- Polymorphism: The equals method in the Review and User classes is overridden to provide specific equality checks.
- Abstraction: The MovieReviewsManager class provides an interface to manage users and reviews, abstracting away the details of how users are stored and how reviews are managed. 


**27/08/2024 Update:** Added Delete function and AdminUser class to leverage inheritance.

## How To Use
1) Open folder in VS Code (or any IDE you use)
2) Type in the bash command prompt to compile all java files: javac *.java
3) Type this to start the program: java MovieReviewsUI

## Languages and Technologies
- Java
