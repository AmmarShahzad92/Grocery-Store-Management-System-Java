public class OrderNode {
    Order order;
    OrderNode next;

    public OrderNode(Order order) {
        this.order = order;
        this.next = null;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderNode getNext() {
        return next;
    }

    public void setNext(OrderNode next) {
        this.next = next;
    }
}
