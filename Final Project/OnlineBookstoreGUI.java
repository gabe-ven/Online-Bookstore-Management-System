//Names: Gabriel Venezia and Jay Nguyen
//Project: Online Bookstore Management System
//Date: Dec 6 2023

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OnlineBookstoreGUI extends JFrame {

    private OnlineBookstoreManagementSystem bookstoreSystem;
    private JTextArea outputTextArea;
    private JPanel buttonPanel;
    private Customer currentCustomer;

    public OnlineBookstoreGUI() {
        bookstoreSystem = new OnlineBookstoreManagementSystem();

        // Set up the main frame
        setTitle("Online Bookstore Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JButton registerCustomerButton = new JButton("Register Customer");
        registerCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerCustomer();
            }
        });

        // Set up the layout
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.add(registerCustomerButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Register new customer with personal info

    public void registerCustomer() {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String contactDetails = JOptionPane.showInputDialog("Enter your contact details:");
        String address = JOptionPane.showInputDialog("Enter your address:");

        Customer customer = new Customer(name, contactDetails, address);
        bookstoreSystem.registerCustomer(customer);
        currentCustomer = customer;

        outputTextArea.setText("Customer registered successfully: " + customer.getName());

        // After registration show other buttons
        newButtons(true);
    }

    // Create buttons after register is done

    public void newButtons(boolean showOptions) {
        buttonPanel.removeAll();

        if (showOptions) {
            JButton browseBooksButton = new JButton("Browse Books");
            browseBooksButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayAvailableBooks();
                }
            });

            JButton addToCartButton = new JButton("Add to Cart");
            addToCartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addToShoppingCart();
                }
            });

            JButton viewCartButton = new JButton("View Cart");
            viewCartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewShoppingCart();
                }
            });

            JButton placeOrderButton = new JButton("Place Order");
            placeOrderButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    placeOrder();
                }
            });

            buttonPanel.add(browseBooksButton);
            buttonPanel.add(addToCartButton);
            buttonPanel.add(viewCartButton);
            buttonPanel.add(placeOrderButton);
        }

        revalidate();
        repaint();
    }

    // Show all books in inventory

    public void displayAvailableBooks() {
        outputTextArea.setText("Available Books:\n");
        for (Book book : bookstoreSystem.getInventory().getAvailabileInventory()) {
            outputTextArea.append(book.getTitle() + " by " + book.getAuthor() + " for " + book.getPrice() + "\n");
        }
    }

    // Add books to shopping cart

    public void addToShoppingCart() {
        String title = JOptionPane.showInputDialog("Enter the title of the book:");
        String author = JOptionPane.showInputDialog("Enter the author of the book:");

        Book book = bookstoreSystem.getInventory().searchBook(title, author);
        if (book != null) {
            bookstoreSystem.addToShoppingCart(currentCustomer, book);
            outputTextArea.setText(book.getTitle() + " added to the shopping cart.");
        } else {
            outputTextArea.setText("Book not found in the inventory.");
        }
    }

    // View current books in shopping cart

    public void viewShoppingCart() {
        ShoppingCart cart = bookstoreSystem.getShoppingCart();
        outputTextArea.setText("Shopping Cart:\n");
        for (Book book : cart.getBooks()) {
            outputTextArea.append(
                    book.getTitle() + " by " + book.getAuthor() + " for " + book.getPrice() + "\n");
        }
    }

    // Place new order

    public void placeOrder() {
        ShoppingCart cart = bookstoreSystem.getShoppingCart();

        if (cart.getBooks().isEmpty()) {
            outputTextArea.setText("Shopping Cart is empty. Add books before placing an order.");
        } else {
            // Create a new Order
            Order order = new Order();

            // Add books from the cart to the order
            for (Book book : cart.getBooks()) {
                order.addBooks(book);
            }

            // Apply any discounts
            order.applyDiscounts();

            // Display order summary
            outputTextArea.setText("Order Summary:\n");
            for (Book book : cart.getBooks()) {
                outputTextArea.append(
                        book.getTitle() + " by " + book.getAuthor() + " for " + book.getPrice() + "\n");
            }

            // Prompt user for payment method, either cash or credit

            String[] options = { "Cash", "Credit" };
            int paymentOption = JOptionPane.showOptionDialog(
                    this,
                    "Choose payment method:",
                    "Payment Method",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (paymentOption == 0) {
                // Cash payment
                processCashPayment(order);
            } else if (paymentOption == 1) {
                // Credit card payment
                processCreditCardPayment(order);
            }
        }
    }

    // Process cash

    public void processCashPayment(Order order) {
        try {
            String cashAmountStr = JOptionPane.showInputDialog("Enter cash amount:");
            double cashAmount = Double.parseDouble(cashAmountStr);

            Payment payment = new Payment(cashAmount);
            double change = payment.giveChange(order.totalCost());

            // Display invoice here
            outputTextArea.append(order.toString());
            newButtons(false);
            if (change > 0) {
                outputTextArea.append("\nChange: $" + change + "\n");
            } else if (change == 0) {
                outputTextArea.append("\nNo change.\n");
            } else {
                outputTextArea.append(
                        "\nInsufficient cash. Additional amount needed: $" + Math.abs(change) + "\n");
            }

            outputTextArea.append("\nOrder placed successfully. Thank you for shopping!\n");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cash amount. Please enter a valid number.");
        }
    }

    // Proccess credit card details

    public void processCreditCardPayment(Order order) {
        String cardNumber = JOptionPane.showInputDialog("Enter your credit card number (16 digits):");
        String expirationDate = JOptionPane.showInputDialog("Enter the expiration date (MMYY):");
        String ccv = JOptionPane.showInputDialog("Enter the CCV (3 digits):");

        Payment payment = new Payment(cardNumber, expirationDate, ccv);

        if (payment.verifyCreditCard()) {
            // Place the order
            newButtons(false);
            outputTextArea.setText("Credit card payment successful. Order placed successfully.\n\n");

            // Display invoice here
            outputTextArea.append(order.toString());
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Credit card payment failed. Please enter valid credit card information.");
        }
    }

    // Main
    public static void main(String[] args) {
        OnlineBookstoreGUI bookstoreGUI = new OnlineBookstoreGUI();
        bookstoreGUI.setVisible(true);

    }
}
