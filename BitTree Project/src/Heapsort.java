import jdk.nashorn.api.tree.BinaryTree;

import java.util.PriorityQueue;

public class Heapsort {

    public static BitTreeNode[] sort(BitTreeNode[] data)
    {
        int length = data.length;
        for(int i = length/2; i >= 0; i--)
        {
            sink(data, i, length - 1);
        }
        while(length > 1)
        {
            exch(data, 0, length - 1);
            length--;
            sink(data, 0, length - 1);
        }
        return data;
    }



    static void sink(BitTreeNode[] data, int size, int length)
    {
        while(2*size  <= length)
        {
            int j = 2*size;
            if(j < length && isLess(data, j, j + 1))
            {
                j++;
            }
            if(!isLess(data, size, j))
            {
                break;
            }
            exch(data, size, j);
            size = j;
        }

    }


    private static boolean isLess(BitTreeNode[] data, int a, int b)
    {

        if(data[a].compareTo(data[b]) < 0)//data[a].getFreq() < data[b].getFreq())
        {
            return true;
        }
        return false;
    }



    static void exch(BitTreeNode[] data, int i, int j) {
        BitTreeNode temp = data[i];
        data[i] = data[j];
        data[j] = temp;

    }
}
