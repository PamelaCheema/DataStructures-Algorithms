public class SeparateChainingHashTable<TKey, TValue> {
    private class Node {
        TKey key;
        TValue value;
        Node next;

        public Node()
        {
            key = null;
            value = null;
            next = null;
        }

        public Node(TKey k, TValue v)
        {
            key = k;
            value = v;
            next = null;
        }

        public Node(TKey k, TValue v, Node nextNode)
        {
            key = k;
            value = v;
            next = nextNode;
        }

        public void setKey(TKey k)
        {
            TKey key = k;
        }
        public void setVal(TValue v)
        {
            TValue value = v;
        }

        public TKey getKey()
        {
            return key;
        }

        public TValue getVal()
        {
            return value;
        }

    }

    private int size;

    private Node[] buckets;

    public SeparateChainingHashTable(int initialCapacity) {
        // ADD YOUR CODE HERE.
        buckets = new SeparateChainingHashTable.Node[initialCapacity];
        size = 0;
    }

    public void put(TKey key, TValue value) {
        // ADD YOUR CODE HERE.
        int hash = Math.abs(key.hashCode()%buckets.length);

        if(buckets[hash] != null)
        {
            Node current = buckets[hash];
            while(current.next != null)
            {
                current = current.next;
            }
            current.next = new Node(key, value);
        }

        buckets[hash] = new Node(key, value);

        if(size++ == buckets.length/2)
        {
            resize(buckets.length * 2);
        }
    }

    public TValue get(TKey key) {
        // ADD YOUR CODE HERE.
        int hash = Math.abs(key.hashCode()%buckets.length);
        SeparateChainingHashTable.Node node = buckets[hash];

        return buckets[hash].getVal();
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return size;
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(size == 0)
        {
            return true;
        }
        return false;
    }

    private void resize(int newBucketCount) {
        // ADD YOUR CODE HERE.

        Node[] newBuckets = new SeparateChainingHashTable.Node[newBucketCount];
        size = 0;
        for(int i = 0; i < size; i++)
        {
            if(newBuckets[(buckets[i].getKey()).hashCode()] != null)
            {
                Node current = newBuckets[(buckets[i].getKey()).hashCode()];
                while(current.next != null)
                {
                    current = current.next;
                }
                current.next = new Node(buckets[i].getKey(), buckets[i].getVal());
            }else{
                newBuckets[(buckets[i].getKey()).hashCode()] = new Node(buckets[i].getKey(), buckets[i].getVal());
            }
            size++;
        }
        buckets = newBuckets;
    }
}