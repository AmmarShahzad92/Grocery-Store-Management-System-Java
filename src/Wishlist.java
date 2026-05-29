public class Wishlist {
    private Node head;

    public void addToWishlist(Product product) {
        if (findNode(product.getId()) != null) {
            return; // Already in wishlist
        }

        Node newNode = new Node(product);
        newNode.next = head;
        head = newNode;
    }

    public void removeFromWishlist(int productId) {
        if (head == null) return;

        if (head.product.getId() == productId) {
            head = head.next;
            return;
        }

        Node current = head.next;
        Node previous = head;

        while (current != null) {
            if (current.product.getId() == productId) {
                previous.next = current.next;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public boolean isInWishlist(int productId) {
        return findNode(productId) != null;
    }

    private Node findNode(int productId) {
        Node current = head;
        while (current != null) {
            if (current.product.getId() == productId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}
