public class BinaryHeapMax<Key extends Comparable<Key>> {
    private Key[] heap;
    private int size;


    public BinaryHeapMax() {

        heap = (Key[]) new Comparable[2];
        size = 0;
    }

    public void insert(Key key) {
        // ADD YOUR CODE HERE.
        if(key == null)
        {
            throw new IllegalArgumentException();
        }

        if(size == heap.length - 1)
        {
            resize(heap.length * 2);
        }

        size++;
        int pos = size;
        heap[pos] = key;
        swim(pos);
    }

    public Key delMax() {

        if(isEmpty())
        {
            throw new IllegalStateException();
        }

        if(size-1 == heap.length/4)
        {
            resize(heap.length/2);
        }

        Key max = heap[1];
        size--;
        heap[1] = heap[size + 1];
        heap[size + 1] = null;
        sink(1);

        return max;
    }

    public Key max() {

        if(heap[1] == null)
        {
            throw new IllegalStateException();
        }else{
            return heap[1];
        }
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        return size == 0;
    }

    public int size() {

        return size;

    }

    private void resize(int capacity) {

        Key[] tempKeys = (Key[]) new Comparable[capacity];
        for(int i = 0; i < heap.length; i++)
        {
            tempKeys[i] = heap[i];
        }
        heap = tempKeys;
    }



    public void sink(int k) {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k+1;
        if (leftChildIdx < size() && heap[smallest].compareTo(heap[leftChildIdx]) < 0){
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size() && heap[smallest].compareTo(heap[rightChildIdx]) > 0) {
            smallest = rightChildIdx;
        }
        if (smallest != k) {

            exch(k, smallest);
            sink(smallest);
        }
    }

    private void swim(int k) {

        int parentIndex;


        if(k >= 2)
        {
            parentIndex = (k/2);
            if(heap[k].compareTo(heap[parentIndex]) > 0)
            {
                exch(k, parentIndex);
                swim(parentIndex);
            }
        }
    }

    public String toString()
    {
        String heapResults = "";
        for(int i = 0; i < heap.length; i++)
        {
            heapResults += heap[i] + "\n";
        }
        return heapResults;
    }

    private void exch(int i, int j) {

        Key temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}