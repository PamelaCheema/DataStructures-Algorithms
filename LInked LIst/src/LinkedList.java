import java.util.*;
public class LinkedList {
    private Node head;

    public void LinkedList()
    {
        head = null;
    }

    public void LinkedList(int n)
    {
        head = new Node(n);

    }

    public static String author()
    {
        return "Cheema, Pamela";
    }

    public boolean isEmpty()
    {
        if(head == null)
        {
            return true;
        }

        return false;
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

    public String toString()
    {
        if(head == null)
        {
            return " --> ";
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

    public int get(int index)
    {
        if(index < 0)
        {
            throw new IllegalArgumentException("Error: Index out of bounds");
        }

        Node current = head;
        int r = -1;
        for(int i = 0; i <= index; i++)
        {
            if(current == null)
            {
                throw new IllegalArgumentException("Error: Index out of bounds");
            }
            if(i == index)
            {
                r = current.data;
            }
            current = current.next;
        }
        return r;
    }

    public boolean contains(int n)
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

    public void add(int n)
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

    public void add(int index, int n)
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


    public int remove()
    {
        if(head == null)
        {
            throw new NoSuchElementException("Element does not exist");
        }

        int headVal = head.data;
        head = head.next;

        return headVal;
    }

    public int remove(int index)
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
            int headVal = head.data;
            head = head.next;
            return headVal;
        }
        Node current = head;
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

        int retVal = current.next.data;
        current.next = current.next.next;
        return retVal;
    }


    public static class Node
    {
        int data;
        Node next;

        public Node ()
        {
            data = 0;
            next = null;
        }

        public Node ( int n)
        {
            data = n;
            next = null;
        }

        public void Node ( int n, Node nextNode)
        {
            data = n;
            next = nextNode;
        }
        public void setData(int n)
        {
            this.data = n;
        }

        public int getData()
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

