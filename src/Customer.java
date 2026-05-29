import java.util.ArrayList;

public class Customer extends User {
    private String name;
    private String phone;
    private ArrayList<Address> address;
    private Cart cart; // Singly Linked List Stack
    private Wishlist wishlist; // Singly Linked List Stack
    private RecentlyViewed recentlyViewed; // Stack
    private OrderHistory orders; // Stack

    public Customer(int id, String username, String password) {
        super(id, username, password);
        this.address = new ArrayList<Address>();
        this.cart = new Cart();
        this.wishlist = new Wishlist();
        this.recentlyViewed = new RecentlyViewed();
        this.orders = new OrderHistory();
    }

    public void addAddress(Address adrs) {
        address.add(adrs);
    }

    public boolean hasAddress() {
        return !address.isEmpty();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public RecentlyViewed getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(RecentlyViewed recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }

    public OrderHistory getOrders() {
        return orders;
    }

    public void setOrders(OrderHistory orders) {
        this.orders = orders;
    }
}
