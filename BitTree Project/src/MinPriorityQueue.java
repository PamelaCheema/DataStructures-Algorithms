public class MinPriorityQueue<T extends Comparable<T>> {
    // ADD YOUR CODE HERE.
    private BitTreeNode[] a;
    private int size = 0;
    private BitTreeNode minVal;
    private int maxValIndex;
    public MinPriorityQueue() {
        // ADD YOUR CODE HERE.
        a = (BitTreeNode[]) new Comparable[1];
        size = 0;
        minVal = null;
        maxValIndex = 0;

    }

    public void insert(BitTreeNode node) {
        // ADD YOUR CODE HERE.
        if(node == null)
        {
            throw new IllegalArgumentException("Unable to insert null item into empty priority queue");
        }

        if(size + 1 == a.length)
        {
            BitTreeNode[] b = (BitTreeNode[]) new Comparable[a.length * 2];
            for(int i = 0; i < a.length; i++)
            {
                b[i] = a[i];
            }
            a = b;
        }

        a[size] = node;

        if ((minVal == null) || (node.getFreq() > (minVal.getFreq()) ))
        {
            minVal = node;
            maxValIndex = size;
        }
        size++;
        return;
    }

   /**public T delMax() {
        // ADD YOUR CODE HERE.
        if(isEmpty())
        {
            throw new IllegalStateException("Unable to delete max of empty priority queue");
        }


        T temp = maxVal;
        a[maxValIndex] = a[--size];
        a[size] = null;
        maxVal = a[0];
        maxValIndex = 0;


        for(int i = 1; i < size; i++)
        {
            if(a[i].compareTo(maxVal) > 0)
            {
                maxVal = a[i];
                maxValIndex = i;
            }

        }
        //size--;
        if(size <= (a.length/4))
        {
            T[] b = (T[]) new Comparable[a.length/2];
            for(int i = 0; i <= size; i++)
            {
                b[i] = a[i];
            }
            a = b;
        }

        return temp;
    }**/


    public BitTreeNode max() {
        // ADD YOUR CODE HERE.
        if(isEmpty())
        {
            throw new IllegalStateException("Unable to get max of empty priority queue");
        }
        return minVal;
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

    public String toString()
    {
        String allElements = "";
        for(int i = 0; i < a.length; i++)
        {
            allElements += a[i].getChr() + ":   " + a[i].getFreq() + "\n";
        }
        return allElements;
    }
}