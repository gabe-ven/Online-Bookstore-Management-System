//This class will represent a book and store information such as title, author, genre, price, and availability.

public class Book {
    private String title, author, genre;
    private double price;
    private boolean available;

    /**
     * Book constructor
     * 
     * @param title
     * @param author
     * @param genre
     * @param price
     * @param available
     */
    public Book(String title, String author, String genre, double price, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.available = available;
    }

    /**
     * Get title of book
     * 
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get author of book
     * 
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get genre of book
     * 
     * @return genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Get price of book
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Check if book is available
     * 
     * @return true or false
     */
    public boolean checkAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Set author of book
     * 
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Set genre of book
     * 
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Set price of book
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set availability
     * 
     * @param true or false
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Make book info into a string
     * 
     * @return String of info
     */
    public String toString() {
        return title + ";" + author + ";" + genre + ";" + price + ";" + available;
    }

}
