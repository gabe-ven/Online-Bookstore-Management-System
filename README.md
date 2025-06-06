# 📚 Online Bookstore Management System

A Java-based GUI application for managing an online bookstore, complete with inventory control, customer registration, shopping cart, order processing, and payment handling.

## 👥 Team
**Gabriel Venezia** & **Jay Nguyen**

---

## 🚀 Features

- 📦 Book Inventory Management (add, update, search, delete)
- 👤 Customer Registration & Info Management
- 🛒 Shopping Cart with real-time updates
- 🧾 Order Processing and Invoice Generation
- 💳 Cash & Credit Card Payment with change calculation
- 🖥️ Java Swing GUI for interactive use

---

## 🧩 Class Overview (CRC Highlights)

| Class         | Key Responsibilities                            | Collaborators       |
|---------------|--------------------------------------------------|---------------------|
| **Book**      | Store title, author, genre, price, availability  | Inventory           |
| **Inventory** | Manage books (add, update, search, delete)       | Book                |
| **Customer**  | Manage customer details                          | Order               |
| **Order**     | Process orders, calculate totals, apply discounts| Customer, Inventory |
| **ShoppingCart** | Add/remove books from cart                    | Order               |
| **Payment**   | Handle cash & card payments, give change         | Customer            |

---

## 🖼️ UML Diagram

[📄 View UML Diagram](https://drive.google.com/file/d/1gjt2RPc9rHw0nuper-FLMlY4XpHXjkkD/view?usp=drive_link)

---

## 📋 Test Plan

| Action                 | Expected Result                                                                 |
|------------------------|----------------------------------------------------------------------------------|
| Register Customer      | Stores valid customer data                                                      |
| Browse Books           | Displays all available inventory items                                          |
| Add to Cart            | Adds valid books to cart, handles errors gracefully                             |
| View Cart              | Displays current cart with details or shows empty message                       |
| Place Order            | Displays summary, verifies items, prevents checkout on empty cart               |
| Payment                | Accepts cash/credit, validates input, calculates change                         |
| GUI Interaction        | All buttons functional, intuitive navigation                                    |

---

## 🧪 Method Documentation

Each class contains well-documented methods using JavaDoc, including constructors, accessors, and action methods such as `addBooks()`, `applyDiscounts()`, `verifyCreditCard()`, etc.

---

## 🛠️ How to Run

### ✅ Prerequisites

- **Java SDK** (Download from [Oracle](https://www.oracle.com/java/technologies/downloads/))
- **Java IDE** (e.g. IntelliJ IDEA, Eclipse, NetBeans)

### ▶️ Steps

1. **Download** the source code and extract it.
2. **Open** the project in your IDE.
3. **Run** the main class: `OnlineBookstoreGUI`.
4. The **GUI** will launch. Explore the app!
5. **Exit** the app by closing the GUI window.

---

## 🧭 Project Structure

OnlineBookstore/
├── src/
│   └── bookstore/
│       ├── Book.java
│       ├── Inventory.java
│       ├── Customer.java
│       ├── Order.java
│       ├── ShoppingCart.java
│       ├── Payment.java
│       └── OnlineBookstoreGUI.java
├── README.md
├── UML_Diagram.pdf
└── docs/
    └── javadoc/


---

## 📌 Notes

- GUI built with **Java Swing**
- Discount handling and payment change logic included
- JavaDoc and UML provided


