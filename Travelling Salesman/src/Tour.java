public class Tour {

    // creates an empty Tour
    Node first;

    public Tour()
    {
        first = null;
    }
    // creates the 4-point Tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d)
    {
        Node aNode = new Node(a);
        Node bNode = new Node(b);
        Node cNode = new Node(c);
        Node dNode = new Node(d);

        first = new Node(aNode.getData());
        first.setNext(bNode);
        bNode.setNext(cNode);
        cNode.setNext(dNode);
        dNode.setNext(aNode);

        //first = aNode;
    }

    public static String author()
    {
        return "Cheema, Pamela";
    }
    // returns the number of points in this Tour
    public int size()
    {
        //Node head = null;
        if(first == null)
        {
            return 0;
        }


        int n = 0;

        Node current = first;

        while(current.next != null)
        {
            n++;
            current = current.next;
        }

        return n;

    }
    // returns the length of this Tour
    public double length()
    {
        if(first.getNext() == first)
        {
            return 0;
        }

        Node current = first;
        double totalDistance = 0.0;
        while(current.getNext() != null)
        {
            totalDistance += current.getData().distanceTo(current.getNext().getData());
            current = current.next;
        }
        return totalDistance;
    }
    // returns a string representation of this Tour
    public String toString()
    {
        Node current = first;
        String allPoints = "";
        if(first == null)
        {
            return allPoints;
        }
        if(first.getNext() == null)
        {
            return first.getData().toString();
        }
        allPoints = current.getData().toString() + "\n";
        current = current.next;
        while(current.getNext() != null)
        {
            allPoints += current.getData().toString() + "\n";
            current = current.next;
        }
        return allPoints;

    }

    // draws this Tour to standard drawing
    public void draw()
    {

        if(first == null)
        {
            return;
        }
        if(first.getNext() == null)
        {
            first.getData().draw();
        }

        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        Node current = first;
        while(current.next != first)
        {
            current.getData().draw();
            current.getData().drawTo(current.next.getData());
        }

        current.getData().drawTo(first.getData());
    }
    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point p)
    {

        if(first == null)
        {
            first = new Node(p);
            first.setNext(first);
            return;
        }
        if(first.getNext() == first)
        {
            first.next = new Node(p);
            first.next.next = first;
            return;
        }

        Node current = first.next;
        double minDistance = first.getData().distanceTo(p);
        Node closestNode = first;

        while(current.getNext() != first)
        {
            if(current.getData().distanceTo(p) < minDistance)
            {
                minDistance = current.getData().distanceTo(p);
                closestNode = current;
            }
        }

        if(current.getData().distanceTo(p) < minDistance)
        {
            minDistance = current.getData().distanceTo(p);
            closestNode = current;
        }

        Node next = closestNode.getNext();
        closestNode.next = new Node(p);
        closestNode.next.next = next;

    }




    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point p)
    {

        if(first == null)
        {
            first = new Node(p);
            first.next = first;
            return;
        }

        if(first.next == first)
        {
            first.next = new Node(p);
            first.next.next = first;
            return;
        }

        double currentTourLength = this.length();
        Node current = first.next;

        double smallestLength = currentTourLength - (first.getData().distanceTo(first.getNext().getData())
                + first.getData().distanceTo(p) + p.distanceTo(first.getNext().getData()));

        Node smallestNode = first;

        while(current.getNext() != first)
        {
            double currentLength = currentTourLength - (current.getData().distanceTo(current.getNext().getData())
                    + current.getData().distanceTo(p) + p.distanceTo(current.getNext().getData()));

            if(currentLength < smallestLength)
            {
                smallestLength = currentLength;
                smallestNode = current;
            }
        }

        double currentLength = currentTourLength - (current.getData().distanceTo(current.getNext().getData())
                + current.getData().distanceTo(p) + p.distanceTo(current.getNext().getData()));

        if(currentLength < smallestLength)
        {
            smallestLength = currentLength;
            smallestNode = current;
        }

        Node next = smallestNode.getNext();
        smallestNode.next = new Node(p);
        smallestNode.next.next = next;
    }
    //tests this class by directly calling all constructors and methods
    public static void main(String[] args)
    {
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        Tour squareTour = new Tour(a, b, c, d);

        int size = squareTour.size();

        System.out.println("Number of points = " + size);

        double length = squareTour.length();

        System.out.println("Tour length = " + length);

        System.out.println(squareTour);
    }



    public static class Node
    {
        Point p;
        Node next;

        public Node()
        {
            p = null;
            next = null;
        }

        public Node(Point n)
        {
            p = n;
            next = null;
        }

        public void Node (Point n, Node nextNode)
        {
            p = n;
            next = nextNode;
        }
        public void setData(Point n)
        {
            p = n;
        }

        public Point getData()
        {
            return p;
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