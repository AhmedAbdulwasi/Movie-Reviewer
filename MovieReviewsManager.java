/*
 * 
 * Class for MovieReviews Functionality and Management
 */
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * MovieReviewsUI
 */
public class MovieReviewsManager {
    private ArrayList<User> userList;
    private ArrayList<Review> reviews; 
    
    private static final Set<Review.Genre> VALID_GENRES = EnumSet.allOf(Review.Genre.class); // Since its Enum.

    int accountid_num = 100;

    public MovieReviewsManager() throws IOException, IOError{
        userList  = new ArrayList<User>(); // Very important, will be used to access Users
        reviews = new ArrayList<Review>(); // Very important, will be used to access Reviews
        try {
            Registration.loadPreregisteredReviews("reviews.txt",userList,reviews);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        

    }
    
    String error_message = null;
    public String getErrorMessage() { // Function to get error messages
        return error_message;
    }
    private String generateUserAccountId() {
        return "" + accountid_num + userList.size();
    }
    
    public User getUserr(String username) {
        
        for (User account : userList) {
        if (account.getUsername().equals(username)) return account; // get the user based off of their username
        }
        return null;
    }
    private static boolean genreExists(Review.Genre genre) {
        return VALID_GENRES.contains(genre); // check if the genre is valid
    }
    private boolean userExists(User user) {
        for (User account : userList) {
            if (account.getUsername().equals(user.getUsername())) return true;
        }
        return false;
    }
    private boolean reviewExists(Review thereview) {
        for (Review review : reviews) { 
          if (thereview.equals(review)) return true;
        }
        return false;
    }
    public void listAllUsers() {
        System.out.println();
        
        for (int i = 0; i < userList.size(); i++) {
            int index = i + 1; // Listing all users and also including an index so it would be "1. User etc."
            System.out.printf("%-2s. ", index);
            userList.get(i).printInfo();
            System.out.println(); 
        }
    }
    public void listAllReviews() {
        System.out.println();
        
        for (int i = 0; i < reviews.size(); i++) {
            int index = i + 1;
            System.out.printf("%-2s. ", index);
            reviews.get(i).printInfo();
            System.out.println(); 
        }
    }
    public void printUser(int ind) {
        System.out.println(); // I want it to print Users profile first then the reviews
        if (ind-1 >= 0 && ind-1 < userList.size()) {
            User user = userList.get(ind-1);
            System.out.print(user.toString());
            System.out.println(); 
            for (int i = 0; i < reviews.size(); i++) {
                if (reviews.get(i).getUser().getUsername().equals(user.getUsername())) {
                    System.out.print(reviews.get(i).toPrint());
                    System.out.println(); 
                }
            }
        } 
        else {
            System.out.println("Invalid review index.");
        }
    }
    public void printReview(int ind) {
        System.out.println();
        if (ind-1 >= 0 && ind-1 < reviews.size()) {
            System.out.print(reviews.get(ind-1).toPrint());
            System.out.println(); 
        } 
        else {
            System.out.println("Invalid review index.");
        }
    }
    public boolean RegisterUser(String username) {
        if (username == null || username.isEmpty()) { // check if they typed incorrectly
            error_message = "Username Is Invalid";
            return false;
        }
        User newuser = new User(generateUserAccountId(), username, 0); // Does this user exists?
        if (userExists(newuser)) {
            error_message = "Username Already Exists";
            return false;
        }
        userList.add(newuser);
        return true;

    }
    public boolean writeReview(String username, String movie_name, double stars, String description, int year, Review.Genre genre) {
        User theuser = getUserr(username); // use the getuser function based off of the username
        if (theuser == null) {
            error_message = "User Account Not Found " + username;
            return false;
        }
        if (stars < 0 || stars > 5) {
            error_message = "Cannot Enter More Than 5 Stars Or Less Than 0 Stars";
            return false;
        }
        if (genre == null || !genreExists(genre)) {
            error_message = "Genre Doesn't Exist";
            return false;
        }
        if (year > 2030 || year < 1940) { // Sorry to movies that are older than 1940.
            error_message = "Invalid Year";
            return false;
        }
        Review thereview = new Review(movie_name, stars, theuser, description, year, genre); // Create a review based off the info
        if (reviewExists(thereview)) {
            error_message = "Review Already Exists";
            return false;
        }
        reviews.add(thereview); // add it to the list
        theuser.addNumbOfReviews(); // increment the number of reviews for that user
        return true;
    }

    public void SavingReview(String outFile) throws IOException {
        PrintWriter writer = null;
      
        try {
          writer = new PrintWriter(outFile); // Write it to outFile which is reviews.txt
          writer.print(""); // clearing
          for (Review rev : reviews) { // each review, write it to reviews.txt
            writer.print(rev.toString() + "\n"); 
          }
        } finally {
          if (writer != null) {
            writer.close();
          }
        }
      }

    //SORTS FOR REVIEWS
    public void sortbystars() { // Sort it by stars
        Collections.sort(reviews, new Comparator<Review>() {
            public int compare(Review review1, Review review2) {
              return Double.compare(review2.getStars(), review1.getStars());
            }
          });
        int ind = 1;
        for (Review rev : reviews) {
            System.out.printf("%-2s. ", ind);
            rev.printInfo();
            ind++;
            System.out.println(); 
        }
    }
    public void sortbydate() { // Sort it by date
        Collections.sort(reviews, new Comparator<Review>() {
            public int compare(Review review1, Review review2) {
              return Integer.compare(review2.getMovieYear(), review1.getMovieYear());
            }
          });
        int ind = 1;
        for (Review rev : reviews) {
            System.out.printf("%-2s. ", ind);
            rev.printInfo();
            ind++;
            System.out.println(); 
        }
    }
    public void sortbymovie() { // Sort by Movie name
        Collections.sort(reviews, new Comparator<Review>() {
            public int compare(Review review1, Review review2) {
              return review1.getMovieName().compareTo(review2.getMovieName());
            }
          });
        int ind = 1;
        for (Review rev : reviews) {
            System.out.printf("%-2s. ", ind);
            rev.printInfo();
            ind++;
            System.out.println(); 
        }
    }

    public void sortbynumb() { // Sort by the number of reviews (recommended)!
        Collections.sort(reviews, new Comparator<Review>() {
            public int compare(Review review1, Review review2) {
              return Integer.compare(review2.getUser().getNumberOfReviews(), review1.getUser().getNumberOfReviews());
            }
          });
        int ind = 1;
        for (Review rev : reviews) {
            System.out.printf("%-2s. ", ind);
            rev.printInfo();
            ind++;
            System.out.println(); 
        }
    }
    // SORT FOR USERS

}