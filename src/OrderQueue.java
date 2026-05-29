import java.util.ArrayList;

public class OrderQueue {
    private OrderNode front;
    private OrderNode rear;

    public OrderQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Order order) {
        OrderNode newNode = new OrderNode(order);

        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Order dequeue() {
        if (front == null) {
            return null;
        }

        Order removedOrder = front.order;
        front = front.next;

        if (front == null) {
            rear = null;
        }

        return removedOrder;
    }

    public ArrayList<Order> displayPendingOrders() {
        ArrayList<Order> result = new ArrayList<Order>();
        OrderNode current = front;

        while (current != null) {
            result.add(current.order);
            current = current.next;
        }

        return result;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public OrderNode getFront() {
        return front;
    }

    public void setFront(OrderNode front) {
        this.front = front;
    }

    public OrderNode getRear() {
        return rear;
    }

    public void setRear(OrderNode rear) {
        this.rear = rear;
    }
}
