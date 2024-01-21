//The Online Bookstore Management System is a Java-based project that aims to create a comprehensive system for managing the operations of an online bookstore. The project will involve creating a set of Java classes to handle different aspects of the system, including book inventory management, customer information, order processing, and more.

import java.util.ArrayList;

public class OnlineBookstoreManagementSystem {
    private Inventory inventory;
    private ShoppingCart shoppingCart;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;

    // Create online bookstore
    public OnlineBookstoreManagementSystem() {
        inventory = new Inventory();
        shoppingCart = new ShoppingCart();
        customers = new ArrayList<>();
        orders = new ArrayList<>();

        // Add new books to bookstore
        inventory.addNewBooks("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 10.99, true);
        inventory.addNewBooks("To Kill a Mockingbird", "Harper Lee", "Fiction", 12.99, true);
        inventory.addNewBooks("1984", "George Orwell", "Dystopian", 9.99, true);
        inventory.addNewBooks("The Catcher in the Rye", "J.D. Salinger", "Coming of Age", 11.99, true);
        inventory.addNewBooks("Pride and Prejudice", "Jane Austen", "Romance", 8.99, true);
        inventory.addNewBooks("The Hobbit", "J.R.R. Tolkien", "Fantasy", 14.99, true);
        inventory.addNewBooks("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "Fantasy", 15.99, true);
        inventory.addNewBooks("The Lord of the Rings", "J.R.R. Tolkien", "Fantasy", 18.99, true);
        inventory.addNewBooks("The Da Vinci Code", "Dan Brown", "Mystery", 13.99, true);
        inventory.addNewBooks("The Alchemist", "Paulo Coelho", "Philosophical", 10.99, true);
        inventory.addNewBooks("Animal Farm", "George Orwell", "Satire", 8.99, true);
        inventory.addNewBooks("The Outsiders", "S.E. Hinton", "Coming of Age", 10.99, true);
        inventory.addNewBooks("Brave New World", "Aldous Huxley", "Dystopian", 12.99, true);
        inventory.addNewBooks("The Road", "Cormac McCarthy", "Post-Apocalyptic", 11.99, true);
        inventory.addNewBooks("Fahrenheit 451", "Ray Bradbury", "Dystopian", 9.99, true);
        inventory.addNewBooks("Blink", "Malcolm Gladwell", "Psychology", 10.99, true);
        inventory.addNewBooks("Outliers", "Malcolm Gladwell", "Psychology", 11.99, true);
        inventory.addNewBooks("The Help", "Kathryn Stockett", "Historical Fiction", 13.99, true);

    }

    // Register customer
    public String registerCustomer(Customer customer) {
        customer.readCustomerFile();

        boolean customerExists = false;

        for (Customer existingCustomer : customers) {
            if (existingCustomer.getName().equals(customer.getName())) {
                customerExists = true;
                break;
            }
        }
        if (customerExists) {
            return "Welcome back " + customer.getName() + "!";
        } else {
            customers.add(customer);
            customer.writeCustomerFile();
            return "New customer registered: " + customer.getName();
        }
    }

    // Browse available books
    public String browseBooks() {
        ArrayList<Book> availableBooks = inventory.getAvailabileInventory();

        if (availableBooks.isEmpty()) {
            return "No books available in inventory";
        } else {
            String booksInfo = "Available Books:\n";
            for (Book book : availableBooks) {
                booksInfo += book.getTitle() + " by " + book.getAuthor() + " for " + book.getPrice() + "\n";
            }
            return booksInfo;
        }
    }

    // Add books to shopping cart
    public String addToShoppingCart(Customer customer, Book book) {
        shoppingCart.addBooks(book);
        return book.getTitle() + " added to shopping cart.";
    }

    // View customer's shopping cart
    public String viewShoppingCart() {
        ArrayList<Book> cartBooks = shoppingCart.getBooks();
        if (cartBooks.isEmpty()) {
            return "Shopping Cart is empty";
        } else {
            String cartInfo = "Shopping Cart:\n";
            for (Book book : cartBooks) {
                cartInfo += book.getTitle() + " by " + book.getAuthor() + " for " + book.getPrice() + "\n";
            }
            return cartInfo;
        }
    }

    // Place an order
    public String placeOrder(Customer customer, String paymentMethod,
            String cardNumber, String expirationDate, String ccv, double cashAmount) {
        Order order = new Order();

        ArrayList<Book> booksInCart = shoppingCart.getBooks();
        for (Book book : booksInCart) {
            order.addBooks(book);
        }

        if (paymentMethod.equals("credit")) {
            Payment payment = new Payment(cardNumber, expirationDate, ccv);

            if (payment.verifyCreditCard()) {
                orders.add(order);
                return "Credit card payment successful. Order placed successfully.\n" + order.toString();
            } else {
                return "Credit card payment failed. Please enter valid credit card information.";
            }
        } else if (paymentMethod.equals("cash")) {
            Payment payment = new Payment(cashAmount);

            if (payment.isCashEqual(order.totalCost()) || payment.overOrUnder(order.totalCost()) > 0) {
                orders.add(order);
                payment.giveChange(order.totalCost());
                return "Cash payment successful. Order placed successfully.\n" + order.toString();
            } else {
                return "Cash payment failed. Amount is not sufficient. Please enter a valid cash amount.";
            }
        } else {
            return "Invalid payment method. Order not placed.";
        }
    }

    // Return customer shopping cart
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    // Return book inventory
    public Inventory getInventory() {
        return inventory;
    }
}
