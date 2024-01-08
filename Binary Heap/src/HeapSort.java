public class HeapSort {


    public static void sort(Comparable[] data) {

        for(int i = 0; i < data.length/2; i++)
        {
            sink(data, data.length, i);
        }
    }

    static void sink(Comparable[] data, int size, int k) {

        int leftChild = (2 * k) + 1;
        int rightChild = ((2 * (k))) + 2;
        if(leftChild >= data.length && rightChild >= data.length)
        {
            return;
        }
        if(data[k].compareTo(data[leftChild]) < 0 && data[k].compareTo(rightChild) < 0)
        {
            return;
        }
        if(data[leftChild].compareTo(data[rightChild]) < 0)
        {
            if(data[k].compareTo(data[leftChild]) > 0)
            {
                exch(data, k, leftChild);
            }
        }else{
            if(data[k].compareTo(data[rightChild]) > 0)
            {
                exch(data, k, rightChild);
            }
        }
    }

    static void exch(Comparable[] data, int i, int j) {

        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }

    /**static boolean inOrder(Comparable[] data)
    {
        for (int i = 0; i < data.length - 1; i++);
        {
            if (data[i].compareTo(data[i + 1]) < 0) {
                return true;
            }
            else {
                return false;
            }
        }
    }**/

}