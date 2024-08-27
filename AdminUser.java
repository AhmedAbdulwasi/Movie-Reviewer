/*
 * 
 * Class for the AdminUser
 */
import java.util.*;
public class AdminUser extends User {
    public AdminUser(String id, String username, int number_of_reviews) {
        super(id, username, number_of_reviews);
    }

    public boolean deleteReview(ArrayList<User> users, String username, ArrayList<Review> reviews, int ind) {
        User theuser = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                theuser = user;
                break;
            }
        }
        if (theuser == null) {
            System.out.println("User not found.");
            return false;
        }
        if (ind-1 < 0 || ind-1 > reviews.size()) {
            System.out.println("Invalid Review Index.");
            return false;
        }
        reviews.remove(ind-1);
        theuser.subNumbOfReviews();
        return true;
    }
}
