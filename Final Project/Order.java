//This class will handle the process of placing orders. It will have methods for adding items to order, calculating the total cost, applying discounts, and generating an invoice.

import java.util.ArrayList;

public class Order {
    private ArrayList<Book> order;
    private double totalCost;
    public static int discountMet = 50;
    public static double discount = 0.10;

    /**
     * Default constructor
     */
    public Order() {
        order = new ArrayList<>();
        totalCost = 0.0;
    }

    /**
     * Add books to order
     * 
     * @param book
     */
    public void addBooks(Book book) {
        order.add(book);
        totalCost += book.getPrice();
    }

    /**
     * Calculate total cost of books
     * 
     * @return total cost
     */
    public double totalCost() {
        return totalCost;
    }

    /**
     * Apply discounts
     */
    public void applyDiscounts() {
        if (totalCost >= discountMet) {
            double discountAmount = discount * totalCost;
            totalCost -= discountAmount;
            System.out.printf("You received a discount of $%.2f!%n", discountAmount);
        }
    }

    /**
     * Generate an invoice
     * 
     * @return String of invoice
     */
    public String toString() {
        String invoice = "\n[INVOICE]\n";
        for (Book book : order) {
            invoice += "Title: " + book.getTitle() + "\n";
            invoice += "Author: " + book.getAuthor() + "\n";
            invoice += "Genre: " + book.getGenre() + "\n";
            invoice += "Price: $" + book.getPrice() + "\n\n";

        }
        applyDiscounts();
        invoice += "Total Cost: $" + totalCost + "\n";

        return invoice;
    }
}
