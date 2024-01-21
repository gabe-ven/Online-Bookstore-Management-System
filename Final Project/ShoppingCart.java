//This class will represent a customer's shopping cart, allowing them to add and remove books before proceeding to checkout.

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Book> cart;

    /**
     * Default constructor
     */
    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    /**
     * Add book to shopping cart
     * 
     * @param book
     */
    public void addBooks(Book book) {
        cart.add(book);
    }

    /**
     * Remove book from shopping cart
     * 
     * @param book
     */
    public void removeBooks(Book book) {
        cart.remove(book);
    }

    /**
     * Return books in cart
     * 
     * @param ArrayList<Book>
     */
    public ArrayList<Book> getBooks() {
        return cart;
    }

    /**
     * Searches for book in shopping cart
     * 
     * @param title
     * @param author
     * @return Book
     */
    public Book searchBook(String title, String author) {
        for (Book book : cart) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }
}
