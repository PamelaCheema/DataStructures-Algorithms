

import java.util.*;

public class DoublyLinkedList<T> {

    private Node<T> head;

    //private int size;


    public DoublyLinkedList()
    {
        this.head = null;
    }

    public DoublyLinkedList(T data)
    {
        this.head = new Node<T>(data);
    }

    public static String author()
    {
        return "Cheema, Pamela";
    }

    public T getHeadData()
    {
        return head.data;
    }

    public boolean isEmpty()
    {
        if(head == null)
        {
            return true;
        }

        return false;
    }

    public T get(int index)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException();
        }

        if(head == null)
        {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current = head;
        Node<T> previous;
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }
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
            previous = current;
            current = current.next;
        }
        return r;
    }

    public boolean contains(T n)
    {
        Node<T> current = head;

        Node<T> previous;

        if(current == null)
        {
            return false;
        }
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }
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
        Node<T> current = head;
        Node<T> previous;
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }

        while(current.next != null)
        {
            previous = current;
            current = current.next;
        }

        previous = current;
        current.next = new Node(n);
    }

    public void add(int index, T n)
    {
        if(index < 0 )
        {
            throw new   IndexOutOfBoundsException();
        }

        if(index == 0)
        {
            Node newHead = new Node(n);
            newHead.next = head;
            head = newHead;
            return;
        }

        Node<T> current = head;

        if(current == null)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<T> previous;
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }

        for(int i = 0; i < index; i++)
        {
            if(current == null)
            {
                throw new IndexOutOfBoundsException();

            }

            previous = current;

            current = current.next;
        }

        if(current == null)
        {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNext = current.next;
        previous = current;
        current.next = new Node(n);
        current.next.next = currentNext;

    }

    public int size()
    {
        if(head == null)
        {
            return 0;
        }

        int n = 1;

        Node<T> current = head;

        Node<T> previous;
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }

        while(current.next != null)
        {
            n++;
            previous = current;
            current = current.next;
        }

        return n;

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

        Node<T> previous;
        if(current.previous == null)
        {
            previous = null;
        }else{
            previous = current.previous;
        }
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
        if(isEmpty())
        {
            return "null";
        }

        String returnStatement = "";

        if(head == null)
        {
            returnStatement = "null";

            return returnStatement;
        }

        Node<T> current = head;

        Node<T> previous;

        if(current.previous == null)
        {
            previous = null;
            //previous = current.previous;
        }


        String nodes = "null <-- " + current.data + " --> ";

        while(current.next != null)
        {
            previous = current;

            current = current.next;

            if(current.next == null)
            {
                nodes += current.data + " --> ";
            }

            nodes += current.data + " <--> ";
        }

        nodes += "null";

        return nodes;
    }

    private static class Node<T>
    {
        private T data;
        private Node<T> next;
        private Node<T> previous;

        public Node()
        {
            this.data = null;
            this.next = null;
            this.previous = null;
        }

        public Node(T n)
        {
            this.data = n;
            this.next = null;
            this.previous = null;
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

        public void setPrevious()
        {
            this.previous = this.next.previous;
        }

        public Node getPrevious()
        {
            return previous;
        }
    }
}