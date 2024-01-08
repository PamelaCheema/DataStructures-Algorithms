import java.util.*;

public class GenericLinkedList<T> {
    private Node<T> head;
    private int size;

    public GenericLinkedList()
    {
        this.head = null;
    }

    public GenericLinkedList(T data)
    {
        this.head = new Node<T>(data);
    }

    public T getHeadData()
    {
        return head.data;
    }

    public int size()
    {
        if(head == null)
        {
            return 0;
        }

        int n = 1;

        Node current = head;
        while(current.next != null)
        {
            n++;
            current = current.next;
        }

        return n;

    }

    public T get(int index)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException();
        }

        Node<T> current = head;
        T r = null;
        for(int i = 0; i <= index; i++)
        {
            if(current == null)
            {
                throw new IndexOutOfBoundsException();
            }
            if(i == index)
            {
                r = current.getData();
            }
            current = current.next;
        }
        return r;
    }

    public boolean contains(T n)
    {
        Node current = head;

        while(current != null)
        {
            if(current.data == n)
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void add(T n)
    {
        if(head == null)
        {
            head = new Node(n);
            return;
        }
        Node current = head;
        while(current.next != null)
        {
            current = current.next;
        }

        current.next = new Node(n);
    }

    public void add(int index, T n)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException("Error: Index out of bounds");
        }



        if(index == 0)
        {
            Node newHead = new Node(n);
            newHead.next = head;
            head = newHead;
            return;
        }


        Node current = head;
        for(int i = 0; i < index; i++)
        {
            if(current == null)
            {
                throw new IllegalArgumentException("Error: Element does not exist");
            }

            current = current.next;
        }

        if(current == null)
        {
            throw new IllegalArgumentException("Error: Index out of bounds");
        }
        Node currentNext = current.next;
        current.next = new Node(n);
        current.next.next = currentNext;

    }

    public T remove()
    {
        if(head == null)
        {
            throw new NoSuchElementException("Element does not exist");
        }

        T headVal = head.data;
        head = head.next;

        return headVal;
    }

    public T remove(int index)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException("Error: Index out of bounds");
        }
        if(head == null)
        {
            throw new NoSuchElementException("Element does not exist");
        }
        if(index == 0)
        {
            T headVal = head.data;
            head = head.next;
            return headVal;
        }
        Node<T> current = head;
        int i = 0;
        while(i < index )
        {
            if(current == null)
            {
                throw new NoSuchElementException("Element does not exist");
            }
            current = current.next;
            i++;
        }

        if(current == null)
        {
            throw new NoSuchElementException("Element does not exist");
        }

        T retVal =  current.next.data;
        current.next = current.next.next;
        return retVal;
    }

    public String toString()
    {
        String returnStatement = "";
        if(head == null)
        {
            returnStatement = "null";
            return returnStatement;
        }
        Node current = head;
        String nodes = current.data + " --> ";
        while(current.next != null)
        {
            current = current.next;
            nodes += current.data + " --> ";
        }
        nodes += "null";
        return nodes;
    }

    private static class Node<T>
    {
        T data;
        Node<T> next;

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

        public void Node ( T n, Node nextNode)
        {
            data = n;
            next = nextNode;
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
    }
}
