public class OrderHistory {
    private OrderNode top;

    public void push(Order order) {
        OrderNode newNode = new OrderNode(order);
        newNode.next = top;
        top = newNode;
    }

    public Order peek() {
        if (top != null) {
            return top.order;
        }
        return null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void clear() {
        top = null;
    }

    public OrderNode getTop() {
        return top;
    }

    public void setTop(OrderNode top) {
        this.top = top;
    }
}
