public class HeapSortRunner {

    public static Integer[] runTest()
    {
        Integer data[] = {2, 9, 3, 4, 5, 7, 6, 0, 1, 8};
        HeapSort.sort(data);
        return data;
    }

    public static void main(String[] args)
    {
        Integer[] returnVal = runTest();
    }
}
