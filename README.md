# Grocery Store Management System

A comprehensive Java-based desktop application for managing a grocery store, providing a feature-rich environment for both customers and administrators. The system uses a graphical user interface (GUI) for easy interaction and is built upon efficient data structures and algorithms for optimal performance.

## Features

### For Customers:
- **User Authentication**: Secure login and registration for customers.
- **Product Browsing**: View products with details like name, price, and category.
- **Search and Filter**: Easily search for products and filter them based on various criteria.
- **Shopping Cart**: Add and remove products from the cart.
- **Wishlist**: Save products for later purchase.
- **Order Management**: Place orders and view order history.
- **Recently Viewed**: Keep track of recently viewed items.

### For Administrators:
- **Dashboard**: An overview of the system's statistics.
- **Product Management**: Add, update, and delete products from the inventory.
- **Order Processing**: View and manage customer orders.
- **Customer Management**: View and manage customer data.

## Technologies and Data Structures

- **Language**: Java
- **User Interface**: Java Swing for the GUI.
- **Data Structures**:
    - **Binary Search Tree**: Used for `ProductTree` to efficiently manage and search for products.
    - **Queue**: Implemented in `OrderQueue` for processing orders in a First-In-First-Out (FIFO) manner.
    - **Linked Lists**: Used for managing `Cart`, `Wishlist`, `OrderHistory`, and `RecentlyViewed` items.
    - **File I/O**: Product and user data are persisted in text files (`dataSheet.txt`).

## How to Get Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- An IDE like Eclipse, IntelliJ IDEA, or VS Code.

### Running the Application
1.  **Clone the repository**:
    ```bash
    git clone https://github.com/AmmarShahzad92/Grocery-Store-Management-System-Java.git
    ```
2.  **Open the project** in your favorite IDE.
3.  **Locate the `Main.java` file** in the `src` directory.
4.  **Compile and run `Main.java`**. This will start the application and open the main GUI window.

## Project Structure
```
/
|-- src/                  # Source code files
|   |-- Main.java         # Main entry point of the application
|   |-- *.java            # Other Java classes for models, UI, and logic
|-- lib/                  # Libraries and dependencies
|-- bin/                  # Compiled output files
|-- README.md             # This file
```

