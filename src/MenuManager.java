import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager {
    private Scanner scanner;
    private Admin admin;
    private Customer customer;

    public MenuManager() {
        scanner = new Scanner(System.in);
        // Sample admin and customer initialization - modify or expand as needed
        admin = new Admin(1, "admin", "admin123");
        customer = new Customer(1, "customer", "customer123");
        // Adding some sample addresses to customer for checkout demo
        customer.addAddress(new Address("Home", "123", "Greenwood", "Metropolis"));
        customer.addAddress(new Address("Work", "456", "TechPark", "Metropolis"));
    }

    public void start() {
        while (true) {
            System.out.println("\nWelcome! Please choose an option:");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Customer");
            System.out.println("3. Exit");
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void adminMenu() {
        if (!loginAdmin()) {
            System.out.println("Invalid admin credentials.");
            return;
        }

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Remove Product");
            System.out.println("4. View Low Stock Products");
            System.out.println("5. View Hot Selling Products");
            System.out.println("6. Manage Deals (Coming Soon)");
            System.out.println("7. View Orders");
            System.out.println("8. Update Order Status");
            System.out.println("9. Logout");
            int choice = readIntInput();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: updateProduct(); break;
                case 3: removeProduct(); break;
                case 4: viewLowStockProducts(); break;
                case 5: viewHotSellingProducts(); break;
                case 6: System.out.println("Manage Deals feature coming soon."); break;
                case 7: viewOrders(); break;
                case 8: updateOrderStatus(); break;
                case 9:
                    System.out.println("Logging out from admin account...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private boolean loginAdmin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }

    private void addProduct() {
        System.out.println("Add New Product:");
        System.out.print("Enter Product ID (integer): ");
        int id = readIntInput();
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        int price = readIntInput();
        System.out.print("Enter Stock Quantity: ");
        int stock = readIntInput();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        boolean success = admin.addProduct(id, name, price, stock, category);
        if (success) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Product with this ID already exists.");
        }
    }

    private void updateProduct() {
        System.out.println("Update Product:");
        System.out.print("Enter Product ID to update: ");
        int id = readIntInput();

        // Search product first
        Product product = admin.getProductTree().searchProduct(id);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Leave input empty to skip updating a field.");

        System.out.print("Enter new name (current: " + product.getName() + "): ");
        String name = scanner.nextLine();
        if (name.isEmpty()) name = null;

        System.out.print("Enter new price (current: " + product.getPrice() + "): ");
        String priceInput = scanner.nextLine();
        int price = priceInput.isEmpty() ? -1 : parseIntOrNegative(priceInput);

        System.out.print("Enter new stock (current: " + product.getStock() + "): ");
        String stockInput = scanner.nextLine();
        int stock = stockInput.isEmpty() ? -1 : parseIntOrNegative(stockInput);

        System.out.print("Enter new category (current: " + product.getCategory() + "): ");
        String category = scanner.nextLine();
        if (category.isEmpty()) category = null;

        boolean updated = admin.updateProduct(id, name, price, stock, category);
        if (updated) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private void removeProduct() {
        System.out.println("Remove Product:");
        System.out.print("Enter Product ID to remove: ");
        int id = readIntInput();

        boolean removed = admin.removeProduct(id);
        if (removed) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewLowStockProducts() {
        System.out.println("Low Stock Products:");
        ArrayList<Product> lowStock = admin.viewLowStockProducts();
        if (lowStock.isEmpty()) {
            System.out.println("No low stock products.");
        } else {
            for (Product p : lowStock) {
                System.out.println(p);
            }
        }
    }

    private void viewHotSellingProducts() {
        System.out.println("Hot Selling Products (Top by Sold Count):");
        ArrayList<Product> hotProducts = admin.viewHotSellingProducts();
        int limit = Math.min(10, hotProducts.size());
        for (int i = 0; i < limit; i++) {
            System.out.println(hotProducts.get(i));
        }
    }

    private void viewOrders() {
        System.out.println("Pending Orders:");
        ArrayList<Order> orders = admin.viewOrders();
        if (orders.isEmpty()) {
            System.out.println("No pending orders.");
        } else {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId() + ", Customer ID: " + order.getCustomerId()
                        + ", Amount: " + order.getOrderAmount() + ", Status: " + order.getStatus()
                        + ", Address: " + order.getShippingAddress());
            }
        }
    }

    private void updateOrderStatus() {
        System.out.println("Update Order Status:");
        System.out.print("Enter Order ID: ");
        int orderId = readIntInput();

        System.out.print("Enter new status (Pending/Shipped/Delivered/Cancelled): ");
        String newStatus = scanner.nextLine();

        boolean updated = admin.updateOrderStatus(orderId, newStatus);
        if (updated) {
            System.out.println("Order status updated.");
        } else {
            System.out.println("Order ID not found.");
        }
    }

    private void customerMenu() {
        if (!loginCustomer()) {
            System.out.println("Invalid customer credentials.");
            return;
        }

        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. See All Products");
            System.out.println("2. See Products by Category");
            System.out.println("3. View Order History");
            System.out.println("4. Update Profile");
            System.out.println("5. View Cart");
            System.out.println("6. View Wishlist");
            System.out.println("7. View Recently Visited Products");
            System.out.println("8. Logout");
            int choice = readIntInput();

            switch (choice) {
                case 1: seeAllProducts(); break;
                case 2: seeProductsByCategory(); break;
                case 3: viewOrderHistory(); break;
                case 4: updateProfile(); break;
                case 5: viewCart(); break;
                case 6: viewWishlist(); break;
                case 7: viewRecentlyVisited(); break;
                case 8:
                    System.out.println("Logging out from customer account...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private boolean loginCustomer() {
        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();
        return username.equals(customer.getUsername()) && password.equals(customer.getPassword());
    }

    private void seeAllProducts() {
        System.out.println("All Products:");
        ArrayList<Product> allProducts = admin.getProductTree().inOrderTraversal();
        if (allProducts.isEmpty()) {
            System.out.println("No products available.");
        } else {
            for (Product p : allProducts) {
                System.out.println(p);
            }
            productListOptions(allProducts);
        }
    }

    private void seeProductsByCategory() {
        System.out.println("Choose category:");
        ArrayList<Product> allProducts = admin.getProductTree().inOrderTraversal();
        ArrayList<String> categories = new ArrayList<>();
        for (Product p : allProducts) {
            if (!categories.contains(p.getCategory())) {
                categories.add(p.getCategory());
            }
        }

        if (categories.isEmpty()) {
            System.out.println("No categories available.");
            return;
        }

        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.println((categories.size() + 1) + ". Back");

        int choice = readIntInput();
        if (choice < 1 || choice > categories.size() + 1) {
            System.out.println("Invalid choice.");
            return;
        }
        if (choice == categories.size() + 1) {
            return; // Back
        }

        String selectedCategory = categories.get(choice - 1);
        ArrayList<Product> categoryProducts = admin.getProductTree().displayByCategory(selectedCategory);
        if (categoryProducts.isEmpty()) {
            System.out.println("No products in this category.");
            return;
        }

        System.out.println("Products in category: " + selectedCategory);
        for (Product p : categoryProducts) {
            System.out.println(p);
        }
        productListOptions(categoryProducts);
    }

    private void productListOptions(ArrayList<Product> products) {
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Expand product details");
            System.out.println("2. Select product by ID");
            System.out.println("3. Go back");
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID to expand: ");
                    int id = readIntInput();
                    Product product = findProductById(products, id);
                    if (product != null) {
                        showProductDetails(product);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID to select: ");
                    int selectId = readIntInput();
                    Product selectedProduct = findProductById(products, selectId);
                    if (selectedProduct != null) {
                        productActions(selectedProduct);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private Product findProductById(ArrayList<Product> products, int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private void showProductDetails(Product product) {
        System.out.println("\nProduct Details:");
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Stock: " + product.getStock());
        System.out.println("Category: " + product.getCategory());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Sold Count: " + product.getSoldCount());
    }

    private void productActions(Product product) {
        while (true) {
            System.out.println("\nActions for product: " + product.getName());
            if (product.getStock() > 0) {
                System.out.println("1. Add to Cart");
            } else {
                System.out.println("1. Add to Wishlist (Product Out of Stock)");
            }
            System.out.println("2. Go back");
            int choice = readIntInput();

            switch (choice) {
                case 1:
                    if (product.getStock() > 0) {
                        addToCart(product);
                    } else {
                        addToWishlist(product);
                    }
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addToCart(Product product) {
        System.out.print("Enter quantity to add: ");
        int quantity = readIntInput();
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }
        if (quantity > product.getStock()) {
            System.out.println("Not enough stock available.");
            return;
        }
        customer.getCart().addToCart(product, quantity);
        customer.getRecentlyViewed().push(product);
        System.out.println("Added to cart.");
    }

    private void addToWishlist(Product product) {
        if (customer.getWishlist().isInWishlist(product.getId())) {
            System.out.println("Product already in wishlist.");
        } else {
            customer.getWishlist().addToWishlist(product);
            customer.getRecentlyViewed().push(product);
            System.out.println("Product added to wishlist.");
        }
    }

    private void viewOrderHistory() {
        System.out.println("Order History:");
        OrderHistory orders = customer.getOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        OrderNode current = orders.getTop();
        while (current != null) {
            Order order = current.getOrder();
            System.out.println("Order ID: " + order.getId() + ", Amount: " + order.getOrderAmount() +
                    ", Status: " + order.getStatus() + ", Address: " + order.getShippingAddress());
            current = current.getNext();
        }
    }

    private void updateProfile() {
        System.out.println("Update Profile:");
        System.out.print("Enter new name (current: " + (customer.getName() == null ? "" : customer.getName()) + "): ");
        String name = scanner.nextLine();
        if (!name.trim().isEmpty()) {
            customer.setName(name);
        }

        System.out.print("Enter new phone (current: " + (customer.getPhone() == null ? "" : customer.getPhone()) + "): ");
        String phone = scanner.nextLine();
        if (!phone.trim().isEmpty()) {
            customer.setPhone(phone);
        }

        System.out.println("Profile updated.");
    }

    private void viewCart() {
        System.out.println("Cart Contents:");
        Cart cart = customer.getCart();
        CartNode current = cart.getHead();
        if (current == null) {
            System.out.println("Cart is empty.");
            return;
        }

        while (current != null) {
            System.out.println("Product: " + current.product.getName() + ", Quantity: " + current.quantity + ", Price each: " + current.product.getPrice());
            current = current.next;
        }
        System.out.println("Total cost: " + cart.findTotalCost());

        System.out.println("\nOptions:");
        System.out.println("1. Checkout");
        System.out.println("2. Go back");
        int choice = readIntInput();
        if (choice == 1) {
            checkout();
        }
    }

    private void checkout() {
        if (!customer.hasAddress()) {
            System.out.println("No address found. Please add an address first.");
            return;
        }
        System.out.println("Select shipping address:");
        ArrayList<Address> addresses = customer.getAddress();
        for (int i = 0; i < addresses.size(); i++) {
            System.out.println((i + 1) + ". " + addresses.get(i));
        }
        int choice = readIntInput();
        if (choice < 1 || choice > addresses.size()) {
            System.out.println("Invalid address selection.");
            return;
        }
        Address selectedAddress = addresses.get(choice - 1);
        Order newOrder = new Order(customer.getId(), customer.getCart(), selectedAddress);
        customer.getOrders().push(newOrder);
        admin.addOrder(newOrder); // Add order to admin's order queue
        customer.setCart(new Cart()); // Clear cart after order
        System.out.println("Order placed successfully! Your Order ID is: " + newOrder.getId());
        System.out.println("Thank you for shopping!");
    }

    private void viewWishlist() {
        System.out.println("Wishlist:");
        Node current = customer.getWishlist().getHead();
        if (current == null) {
            System.out.println("Wishlist is empty.");
            return;
        }
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }

    private void viewRecentlyVisited() {
        System.out.println("Recently Visited Products:");
        Node current = customer.getRecentlyViewed().getTop();
        if (current == null) {
            System.out.println("No recently viewed products.");
            return;
        }
        while (current != null) {
            System.out.println(current.product);
            current = current.next;
        }
    }

    /* Utility methods */

    // Read input and handle exceptions
    private int readIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    // Parsing int or return -1 for empty or invalid input
    private int parseIntOrNegative(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

