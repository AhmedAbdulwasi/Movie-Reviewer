// DO NOT DELETE THE REVIEWS.TXT. IF YOU WANT TO MODIFY IT A LITTLE THEN OK BUT DONT CHANGE THE FORMAT. THE PROGRAM WONT WORK.
// Errors
import java.io.IOError;
import java.io.IOException;
import java.io.FileNotFoundException;
// ALL java classes and interfaces
import java.util.*;

/**
 * MovieReviewsUI
 * This is a project that I've been thinking about for about a year. Was thinking to implement it in HTML/CSS or in Python, or in JS but now I chose Java (Explained in the README.md).
 * Warning, you can not remove your review (except through the txt file saved). 
 */
public class MovieReviewsUI {

    public static void main(String[] args) throws IOException, IOError {

        MovieReviewsManager system = new MovieReviewsManager(); // Will be used to access certain commands from MovieReviewsManager.java
        System.out.println("Welcome to Ahm's Movie Reviewer");
        System.out.println("You got a movie/tv show to write a review about? Write it here!");
        System.out.println("To get started, read the Instructions.txt file for commands.");
        System.out.println("");
        Scanner scanner = new Scanner(System.in); // Read in user's input
        System.out.print(">"); 

        while (scanner.hasNextLine()) { // Where we will process the commands typed by the user
            String action = scanner.nextLine(); // What the command is

            if (action == null || action.equals("")) {
                // Typed something different or just hit enter? This will just skip it and you can type again
                System.out.print("\n>");
                continue;
            }
            else if (action.equalsIgnoreCase("QUIT") || action.equalsIgnoreCase("Q")) { 
                // command to end the program
                System.out.println("Thank you for using Ahm's Movie Reviewer!");
                return;
            }
            else if (action.equalsIgnoreCase("REGUSER")) {
                // Register a user
                String name = "";
                System.out.print("Username: ");
                if (scanner.hasNextLine()) {
                    name = scanner.nextLine();
                }
                
                if (system.RegisterUser(name)) System.out.printf("Welcome %s!",name);
                else System.out.println(system.getErrorMessage()); 
            }
            else if (action.equalsIgnoreCase("REVIEWS")) {
                // Lists all reviews
                system.listAllReviews();
            }
            else if (action.equalsIgnoreCase("USERS")) {
                // Lists all users in the system
                system.listAllUsers();
            }
            else if (action.equalsIgnoreCase("ADDREVIEW")) {
                // Adding a review
                String name = "";
                System.out.print("Username: ");
                if (scanner.hasNextLine()) {
                    name = scanner.nextLine();
                }
                String movie_name = "";
                System.out.print("Movie Name: ");
                if (scanner.hasNextLine()) {
                    movie_name = scanner.nextLine();
                }
                int movie_year = 0;
                System.out.print("Movie Year: ");
                if (scanner.hasNextLine()) {
                    movie_year = scanner.nextInt();
                }
                double stars = 0.0;
                System.out.print("Stars (?/5): ");
                if (scanner.hasNextLine()) {
                    stars = scanner.nextDouble();
                    scanner.nextLine();
                }
                String genreString = "";
                System.out.print("Genre (SCIFI, DRAMA, COMEDY, HORROR, ACTION, OTHER): ");
                if (scanner.hasNextLine()) {
                    genreString = scanner.nextLine();
                }
                String description = "";
                System.out.print("Description (No new line): ");
                if (scanner.hasNextLine()) {
                    description = scanner.nextLine();
                }
                Review.Genre genre = null;
                try {
                  genre = Review.Genre.valueOf(genreString.toUpperCase());  
                } catch (IllegalArgumentException e ) {
                    System.out.println("Invalid genre entered. Defaulting to OTHER.");
                    genre = Review.Genre.OTHER; 
                }

                if (system.writeReview(name,movie_name,stars,description,movie_year,genre)) System.out.print("Sucessfully Written A Review For " + movie_name + " (" + movie_year + ")");
                else System.out.println(system.getErrorMessage());
            }
            else if (action.equalsIgnoreCase("CHECKREVIEW")) {
                // checks a certain review to see the full review made
                int index = 0;
                System.out.print("Index Number: ");
                if (scanner.hasNextLine()) {
                    index = scanner.nextInt();
                }
                system.printReview(index);
            }
            else if (action.equalsIgnoreCase("SORTBYSTARS")) {
                // Sorts by stars
                system.sortbystars();
            }
            else if (action.equalsIgnoreCase("SORTBYDATE")) {
                // Sorts by movie date
                system.sortbydate();
            }
            else if (action.equalsIgnoreCase("SORTBYMOVIE")) {
                // Sorts by movie name
                system.sortbymovie();
            }
            else if (action.equalsIgnoreCase("SORTBYNUMB")) {
                // Sorts by the number of reviews
                system.sortbynumb();
            }
            else if (action.equalsIgnoreCase("CHECKUSER")) {
                // You check a user's reviews
                int index = 0;
                System.out.print("Index Number: ");
                if (scanner.hasNextLine()) {
                    index = scanner.nextInt();
                }
                system.printUser(index);
            }
            else if (action.equalsIgnoreCase("SAVE")) {
                // Save the reviews list into the reviews.txt
                // Optional: Send a request in github to share your review with me and everyone else that uses this java app.
                // Plus I will check these reviews and requests regularly since I love watching new movies.
                // Make sure to type the extension as well so > reviews.txt
                system.SavingReview("reviews.txt");
                System.out.println("Saved successfully!");
            }


            System.out.print("\n>"); // continue if the user typed something that isn't part of the command
        }
        scanner.close();
    }
}