//This class will store customer information, including name, contact details, and address. It will also provide methods for registering new customers and managing their information.

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Customer {
    private String name;
    private String contactDetails;
    private String address;
    private boolean hasDiscount;
    public static final String CUSTOMER_FILE = "customer.txt";

    /**
     * Construct new customer
     * 
     * @param name
     * @param contactDetails
     * @param address
     */
    public Customer(String name, String contactDetails, String address) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.address = address;
        this.hasDiscount = false;
    }

    /**
     * Get name of customer
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get address of customer
     * 
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get contact details of customer
     * 
     * @return contactDetails
     */
    public String getContactDetails() {
        return contactDetails;
    }

    /**
     * Set name of customer
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set address of customer
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set contact details of customer
     * 
     * @param contact details
     */
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    /**
     * See if customer has a discount to use
     * 
     * @return true or false
     */
    public boolean hasDiscount() {
        return hasDiscount;
    }

    /**
     * Write customer information to file
     */
    public void writeCustomerFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE, true))) {
            out.println(name + ";" + contactDetails + ";" + address + ";" + hasDiscount);
        } catch (IOException e) {
            System.out.println("Error writing to the customer file.");
        }
    }

    /**
     * Read customer information from file
     */
    public void readCustomerFile() {
        try (Scanner sc = new Scanner(new File(CUSTOMER_FILE))) {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(";");
                if (parts.length >= 4) {
                    String customerName = parts[0];
                    String customerContactDetails = parts[1];
                    String customerAddress = parts[2];
                    boolean customerHasDiscount = Boolean.parseBoolean(parts[3]);

                    Customer customer = new Customer(customerName, customerContactDetails, customerAddress);
                    customer.hasDiscount = customerHasDiscount;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Customer file not found. Creating a new one.");
        }
    }

}
