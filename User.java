/*
 * 
 * Class for the User of the review
 */
public class User {
    private String accountId;
    private String username;


    private int number_of_reviews;

    public User(String accountId, String username, int number_of_reviews) {
        this.accountId = accountId;
        this.username = username;
        this.number_of_reviews = number_of_reviews;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setNumberOfReviews(int number_of_reviews) {
        this.number_of_reviews = number_of_reviews;
    }

    public int getNumberOfReviews() {
        return number_of_reviews;
    }

    public void addNumbOfReviews() {
        this.number_of_reviews++;
    }
    public void subNumbOfReviews() {
        this.number_of_reviews--;
    }
    public void printInfo() { // Will be used as a User Profile info
        System.out.printf("Id: %s Username: %s Number of Reviews: %d%n", accountId, username, number_of_reviews);
    }
    public String toString() {
        return "Id: " + accountId + " Username: " + username + " Number of Reviews: " + number_of_reviews;
    }

    public boolean equals(Object other) { // To check the user, we will be using accountId.
        User otherUser = (User) other;
        return this.accountId.equals(otherUser.accountId);

    }
}
