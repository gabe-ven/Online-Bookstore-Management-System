//This class will manage the inventory of books in the online bookstore. It will provide methods for adding new books, updating book details, searching for books, and removing books from the inventory. 

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Inventory {

    private ArrayList<Book> bookInventory = new ArrayList<>();
    public static final String INVENTORY_FILE = "inventory.txt";

    /**
     * Add new books to the inventory
     * 
     * @param title
     * @param author
     * @param genre
     * @param price
     * @param available
     */
    public void addNewBooks(String title, String author, String genre, double price, boolean available) {
        Book book = new Book(title, author, genre, price, true);
        bookInventory.add(book);
        writeInventory();
    }

    /**
     * Update a book’s details
     * 
     * @param title
     * @param author
     * @param genre
     * @param price
     * @param availability
     */
    public void updateBookDetails(String title, String author, String genre, double price, boolean available) {
        for (Book book : bookInventory) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                book.setGenre(genre);
                book.setPrice(price);
                book.setAvailable(available);
                writeInventory();
                return;
            }
        }
    }

    /**
     * Search for books
     * 
     * @param title
     * @param author
     * @return Book
     */
    public Book searchBook(String title, String author) {
        for (Book book : bookInventory) {
            if (book.getTitle().equals(title) && book.getAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Remove books from inventory
     * 
     * @param title
     */
    public void removeBooks(String title) {
        for (int i = 0; i < bookInventory.size(); i++) {
            Book book = bookInventory.get(i);
            if (book.getTitle().equals(title)) {
                bookInventory.remove(i);
                i--;
                writeInventory();
            }
        }
    }

    /**
     * Get the list of available books in the inventory
     * 
     * @return ArrayList<Book>
     */

    public ArrayList<Book> getAvailabileInventory() {
        ArrayList<Book> availableBooks = new ArrayList<>();
        for (Book book : bookInventory) {
            if (book.checkAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    /**
     * Read the book inventory
     */
    public void readInventory() {
        try (Scanner sc = new Scanner(new File(INVENTORY_FILE))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(";");
                String title = parts[0];
                String author = parts[1];
                String genre = parts[2];
                double price = Double.parseDouble(parts[3]);
                boolean available = Boolean.parseBoolean(parts[4]);
                Book book = new Book(title, author, genre, price, available);
                bookInventory.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory file not found.");
        }
    }

    /**
     * Write the book inventory
     */
    public void writeInventory() {
        try (PrintWriter out = new PrintWriter(INVENTORY_FILE)) {
            for (Book book : bookInventory) {
                out.println(book.toString());
            }
        } catch (IOException e) {
            System.out.println("Error writing to the inventory file.");
        }
    }
}
