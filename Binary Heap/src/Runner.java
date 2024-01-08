public class Runner {
    public static Integer[] runTest()
    {
        BinaryHeapMax<Integer> pq = new BinaryHeapMax<Integer>();
        Integer[] results = new Integer[10];
        /**System.out.println("There are " + pq.size() + " items in the priority queue");
        System.out.println("The priority queue is empty: " + pq.isEmpty());**/
        /**for (int i = 0; i < 10; i++) {
            pq.insert(i);
        }**/
        int[] data = {88, 86, 73, 79, 58, 13, 11, 54, 32, 29, 27, 59, 21};

        for(int i = 0; i < data.length; i++)
        {
            pq.insert(data[1]);
        }

        System.out.println(pq);

       /** System.out.println("There are " + pq.size() + " items in the priority queue");
        System.out.println("The priority queue is empty: " + pq.isEmpty());
        TestHelper.validateHeapInvariant(pq);
        for (int i = 0; !pq.isEmpty(); i++) {
            results[i] = pq.delMax();
        }
        System.out.println("There are " + pq.size() + " items in the priority queue");
        System.out.println("The priority queue is empty: " + pq.isEmpty());**/
        return results;
    }

    public static void main(String[] args)
    {
        Integer[] returnVal = runTest();
    }
}
