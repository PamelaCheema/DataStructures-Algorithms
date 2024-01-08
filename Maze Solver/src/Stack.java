public class Stack<T> {
    // ADD YOUR CODE HERE.
    private T[] a;
    private int index;
    public Stack() {
        // ADD YOUR CODE HERE.
        a = (T[]) new Object[1];
        index = 0;
    }

    public void push(T newItem) {
        // ADD YOUR CODE HERE.
        if(index == a.length)
        {
            T[] b = (T[]) new Object[a.length * 2];
            for(int i = 0; i < a.length; i++)
            {
                b[i] = a[i];
            }
            a = b;
            a[index] = newItem;
            index++;
            return;
        }
        a[index] = newItem;
        index++;
        return;
    }

    public T pop() {
        // ADD YOUR CODE HERE.

        if(index == (a.length/4))
        {
            T[] b = (T[]) new Object[a.length/2];
            for(int i = 0; i < index; i++)
            {
                b[i] = a[i];
            }
            a = b;
        }
        index--;
        return a[index];
    }

    public T peek() {
        // ADD YOUR CODE HERE.
        return a[index - 1];
    }

    public boolean isEmpty() {
        // ADD YOUR CODE HERE.
        if(index <= 0)
        {
            return true;
        }
        for(int i = 0; i < a.length; i++)
        {
            if(a[i] != null)
            {
                return false;
            }
        }
        return true;
    }

    public int size() {
        // ADD YOUR CODE HERE.
        return index;
    }


}