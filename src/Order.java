import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private Cart cart;
    private Address shippingAddress;
    private LocalDateTime dateTime;
    private int orderAmount;
    private String status;

    private static int orderCount;

    public Order(int customerId, Cart cart, Address shippingAddress) {
        this.id = orderCount++;
        this.customerId = customerId;
        this.cart = cart;
        this.shippingAddress = shippingAddress;
        this.dateTime = LocalDateTime.now();
        this.orderAmount = cart.findTotalCost();
        this.status = "Pending";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
