/*
 * 
 * Class for Registration
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Registration {
    private static int generateid = 100;
    private static Map<String, User> userMap = new HashMap<>();
    
    public static String generateUserAccountId(ArrayList<User> allusers) {
        return "" + generateid + allusers.size();
    }
    //public static void loadPreregisteredUsers(ArrayList<User> users)
    //{
    //    users.add(new User(generateUserAccountId(users), "TestReviewer1",2));
    //    users.add(new User(generateUserAccountId(users), "TestReviewer2",1));
    //}
    //public static void firstreviews(ArrayList<Review> reviews, ArrayList<User> users) { //public Review(String movie_name, double stars, User user, String description, int movie_year, Genre genre)
    //  reviews.add(new Review("Knvies Out", 4.6, users.get(0), "SO MANY TWISTS. FUNNY CASTS. AMAZING SCORE! 4.6/5", 2019, Review.Genre.COMEDY));
    //  reviews.add(new Review("Vivarium", 3.1, users.get(0), "Uhh, what? idk how to describe it but it was a weird plot about 2 people being stuck in a vivarium-like neighbourhood raising an alien. ok? idk. ", 2019, Review.Genre.HORROR));
    //  reviews.add(new Review("Zodiac", 4.3, users.get(1), "This movie focuses on the real-life character, Robert Graysmith who gives up his wife to focus on the Zodiac Investigation. There is a powerful scene at the end where Robert (Jake Gyllenhall) visits a store and where he believes the Zodiac Killer is employed in. They both stare at each other coldly and Robert leaves. Years later, a survivor identifies that employer that Robert visited as the Zodiac Killer. So many great scenes. Beautiful movie", 2007, Review.Genre.HORROR));
    //}
    //public static void loadUsersWithMap(ArrayList<User> users, Map<String, User> userMap) {
    //    for (User user : users) {
    //      userMap.put(user.getUsername(), user); // Just mapping out the users using their key username.
    //    }
    //  }
    // Ignore the above.
    public static void loadPreregisteredReviews(String filename, ArrayList<User> users, ArrayList<Review> reviews) throws FileNotFoundException, IOException, NoSuchElementException, NumberFormatException {
      Map<String,Integer> numbreview = new HashMap<>(); // This one took me a while because it had a unique problem.
      try (Scanner scanner = new Scanner(new File(filename))) { // First what if there are no users at all? then we create a new user
          while (scanner.hasNextLine()) { // Second and this is the biggest, how do we increment the number of reviews? I needed external help. 
              scanner.nextLine(); // Skip "Username:"
              String username = scanner.nextLine().trim();
              scanner.nextLine(); // Skip "Movie_Name:"
              String movieName = scanner.nextLine().trim();
              scanner.nextLine(); // Skip "Stars:"
              double stars = Double.parseDouble(scanner.nextLine().trim());
              scanner.nextLine(); // Skip "Year:"
              int year = Integer.parseInt(scanner.nextLine().trim());
              scanner.nextLine(); // Skip "Genre:"
              Review.Genre genre = Review.Genre.valueOf(scanner.nextLine().trim().toUpperCase());
              scanner.nextLine(); // Skip "Description:"
              String description = scanner.nextLine().trim();

              User user = userMap.get(username); // Use it for the increment of reviews.
              if (user == null) { // If there is nothing in user, create a new one with default value of number of reviews is 0 +1 because we counting how many reviews.
                user = new User(generateUserAccountId(users), username, numbreview.getOrDefault(username, 0)+1);
                users.add(user);
                userMap.put(username, user);
                numbreview.put(username,numbreview.getOrDefault(username, 0)+1);
              } else {
                user.setNumberOfReviews(numbreview.get(username) + 1); // if there is a user, increment!
                numbreview.put(username,numbreview.get(username) + 1);
              }

              Review review = new Review(movieName, stars, user, description, year, genre);
              reviews.add(review);

              if (scanner.hasNextLine()) {
                  scanner.nextLine(); // Just in case if there is additional space
              }
          }
      }
  }

    
}
