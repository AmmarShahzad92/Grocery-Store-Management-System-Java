public class RecentlyViewed {
    private Node top;

    public void push(Product product) {
        Node newNode = new Node(product);
        newNode.next = top;
        top = newNode;
    }

    public Product peek() {
        if (top != null) {
            return top.product;
        }
        return null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }
}
