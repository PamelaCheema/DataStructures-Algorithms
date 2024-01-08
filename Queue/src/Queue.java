public class Queue<T> {
    // ADD YOUR CODE HERE.
    private T[] array;
    private int last = 0;
    private int front = 0;
    private int size;
    public Queue(int maxSize) {
        // ADD YOUR CODE HERE.
        array = (T[]) new Object[maxSize];
        size = 0;
    }

    public void enqueue(T newItem) {
        // ADD YOUR CODE HERE.
        if(last == array.length - 1)
        {
            last = front;
        }
        array[last] = newItem;
        size++;
        last++;
        return;
    }

    public T dequeue() {
        // ADD YOUR CODE HERE.
        //array[front] = null;
        if(front == array.length - 1)
        {
            front = 0;
        }
        front++;
        size--;
        return array[front];
    }

    public T peek() {
        // ADD YOUR CODE HERE.
        return array[front];
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(array[front] == null)
        {
            return true;
        }
        return false;
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return size;
    }

    public static void main(String[] args)
    {

    }
}