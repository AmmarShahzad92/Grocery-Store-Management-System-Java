public class CartNode {
    Product product;
    int quantity;
    CartNode next;

    public CartNode(Product product, int qty) {
        this.product = product;
        this.quantity = qty;
        this.next = null;
    }
}
