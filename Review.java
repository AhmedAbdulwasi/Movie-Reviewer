/*
 * 
 * Class for the review
 */
public class Review {
    private String movie_name;
    private double stars;
    private User user;
    private String description;
    private int movie_year;
    public enum Genre {SCIFI, DRAMA, COMEDY, HORROR, ACTION, OTHER}; // Currently the largest genres. There are many more but I don't want to make the code complicating.
    private Genre genre;
    public Review(String movie_name, double stars, User user, String description, int movie_year, Genre genre) {
        this.movie_name = movie_name;
        this.stars = stars;
        this.user = user;
        this.description = description;
        this.movie_year = movie_year;
        this.genre = genre;
    }
    public void setMovieName(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovieName() {
        return movie_name;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public double getStars() {
        return stars;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMovieYear(int movie_year) {
        this.movie_year = movie_year;
    }

    public int getMovieYear() {
        return movie_year;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
    public String toString() {
        return "Username: " + "\n" + user.getUsername() + "\n"  + "Movie_Name: " + "\n" + movie_name + "\n"  + "Stars: " + "\n" + stars + "\n"  + "Year: " + "\n" + movie_year + "\n"  + "Genre: " + "\n" + genre + "\n"  + "Description: " + "\n" + description + "\n";
    }
    public String toPrint() {
        return "Username: " + "\n" + user.getUsername() + "\n" + "\n"  + "Movie_Name: " + "\n" + movie_name + "\n" + "\n"  + "Stars: " + "\n" + stars + "\n" + "\n"  + "Year: " + "\n" + movie_year + "\n" + "\n"  + "Genre: " + "\n" + genre + "\n" + "\n"  + "Description: " + "\n" + description;
    }
    public void printInfo() { // Printing the review
        System.out.printf("Username: %-20s Movie_Name: %-20s Stars: %.1f/5 Year: %d Genre: %s%n Description: %s", user.getUsername() ,movie_name, stars, movie_year, genre, description);
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Review otherReview = (Review) other;
        return movie_year == otherReview.movie_year && movie_name.equals(otherReview.movie_name);
    }
}
