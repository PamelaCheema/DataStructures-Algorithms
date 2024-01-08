public class Deque<T> {
    private class Node {
        // ADD YOUR CODE HERE.
        T data;
        Node next;
        Node previous;
        public Node()
        {
            this.data = null;
            this.next = null;
        }

        public Node(T n)
        {
            this.data = n;
            this.next = null;
        }

        public void Node ( T n, Node nextNode, Node previousNode)
        {
            data = n;
            next = nextNode;
            previous = previousNode;
        }
        public void setData(T n)
        {
            this.data = n;
        }

        public T getData()
        {
            return data;
        }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node getNext()
        {
            return next;
        }
        public void setPrevious(Node next)
        {
            this.previous = next;
        }

        public Node getPrevious()
        {
            return previous;
        }
    }

    // ADD YOUR CODE HERE.
    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        // ADD YOUR CODE HERE.
        head = null;
        tail = null;
        size = 0;
    }

    public void pushRight(T newItem) {
        // ADD YOUR CODE HERE.
        Node temp = new Node(newItem);

        if(head == null)
        {
            head = temp;
            tail = temp;
        }

        tail.next = temp;
        tail = tail.next;

        size++;
        return;
    }

    public void pushLeft(T newItem) {
        // ADD YOUR CODE HERE.
    }

    public T popRight() {
        // ADD YOUR CODE HERE.
        if(tail == null)
        {
            return null;
        }
        return tail.data;
    }

    public T popLeft() {
        // ADD YOUR CODE HERE.
        T temp = head.getData();
        if(head == null)
        {
            return null;
        }
        if(head == tail)
        {
            head = null;
            tail = null;

        }else{
            head = head.next;
        }
        size--;
        return temp;
    }


    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(size == 0)
        {
            return true;
        }
        return false;
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return size;
    }
}