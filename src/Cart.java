public class Cart {
    private CartNode head;

    public void addToCart (Product product, int quantity) {
        CartNode existing = findNode(product.getId());

        if(existing != null) {
            existing.quantity += quantity;
            return;
        }

        CartNode newNode = new CartNode(product, quantity);
        newNode.next = head;
        head = newNode;
    }

    public void removeFromCart (int productId) {
        if(head == null) {
            return;
        }

        if(head.product.getId() == productId) {
            head = head.next;
            return;
        }

        CartNode current = head.next;
        CartNode previous = head;

        while(current != null) {
            if(current.product.getId() == productId) {
                previous.next = current.next;
                return;
            }
            previous = current;
            current = current.next;

        }
    }

    public void updateQuantity(int productId, int newQuantity) {
        if (newQuantity <= 0) {
            removeFromCart(productId);
            return;
        }

        CartNode node = findNode(productId);
        if (node != null) {
            node.quantity = newQuantity;
        }
    }

    public int findTotalCost() {
        int total = 0;
        CartNode current = head;

        while (current != null) {
            total += current.product.getPrice() * current.quantity;
            current = current.next;
        }

        return total;
    }


    private CartNode findNode (int productId) {
        CartNode current = head;

        while(current != null) {
            if(current.product.getId() == productId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public CartNode getHead() {
        return head;
    }

    public void setHead(CartNode head) {
        this.head = head;
    }
}
