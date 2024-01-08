public class Queue<T> {
    // ADD YOUR CODE HERE.
    private T[] array;
    private int last = 0;
    private int front = 0;
    private int items;
    public Queue() {
        // ADD YOUR CODE HERE.
        array = (T[]) new Object[1];
        items = 0;
    }

    public void enqueue(T newItem) {
        // ADD YOUR CODE HERE.
        array[last] = newItem;
        items++;

        if(last + 1 == array.length)
        {
            T[] b = (T[]) new Object[array.length * 2];
            for(int i = 0; i < array.length; i++)
            {
                b[i] = array[i];
            }
            array = b;
            last++;
            return;
        }

        last++;
        return;
    }

    public T dequeue() {
        // ADD YOUR CODE HERE.
        T temp = array[front];
        array[front] = null;
        front++;
        items--;
        if(items == (array.length/4))
        {
            T[] b = (T[]) new Object[array.length/2];
            for(int i = 0; i < items; i++)
            {
                b[i] = array[front++];
            }
            array = b;
            last = items;
            front = 0;
        }
        return temp;
    }

    public T peek() {
        // ADD YOUR CODE HERE.
        return array[front];
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(items == 0)
        {
            return true;
        }
        return false;
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return items;
    }

}