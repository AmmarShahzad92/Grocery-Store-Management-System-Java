import java.util.ArrayList;

public class Admin extends User {
    private ProductTree productTree;
    private OrderQueue orderQueue;

    public Admin(int id, String username, String password) {
        super(id, username, password);
        this.productTree = new ProductTree();
        this.orderQueue = new OrderQueue();
    }

    public boolean addProduct(int id, String name, int price, int stock, String category) {
        if (productTree.searchProduct(id) != null) {
            return false;
        }

        Product newProduct = new Product(id, name, price, stock, category);
        productTree.insertProduct(newProduct);
        return true;
    }

    public boolean updateProduct(int productId, String name, int price, int stock, String category) {
        Product product = productTree.searchProduct(productId);
        if (product == null) {
            return false;
        }

        if (name != null) {
            product.setName(name);
        }
        if (price > 0) {
            product.setPrice(price);
        }
        if (stock >= 0) {
            product.setStock(stock);
        }
        if (category != null) {
            product.setCategory(category);
        }

        return true;
    }

    public boolean removeProduct(int productId) {
        Product product = productTree.searchProduct(productId);
        if (product == null) {
            return false;
        }

        productTree.deleteProduct(productId);
        return true;
    }

    // Inventory Analysis
    public ArrayList<Product> viewLowStockProducts() {
        return productTree.displayLowStock();
    }

    public ArrayList<Product> viewHotSellingProducts() {
        ArrayList<Product> allProducts = productTree.inOrderTraversal();
        ArrayList<Product> hotProducts = new ArrayList<Product>();

        for (int i = 0; i < allProducts.size(); i++) {
            for (int j = i + 1; j < allProducts.size(); j++) {
                if (allProducts.get(i).getSoldCount() < allProducts.get(j).getSoldCount()) {
                    Product temp = allProducts.get(i);
                    allProducts.set(i, allProducts.get(j));
                    allProducts.set(j, temp);
                }
            }
        }

        return allProducts;
    }

    public ArrayList<Order> viewOrders() {
        return orderQueue.displayPendingOrders();
    }

    public boolean updateOrderStatus(int orderId, String newStatus) {
        ArrayList<Order> orders = orderQueue.displayPendingOrders();

        for (Order order : orders) {
            if (order.getId() == orderId) {
                order.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    public Order processNextOrder() {
        return orderQueue.dequeue();
    }

    public void addOrder(Order order) {
        orderQueue.enqueue(order);
    }

    public ProductTree getProductTree() {
        return productTree;
    }

    public void setProductTree(ProductTree productTree) {
        this.productTree = productTree;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }
}