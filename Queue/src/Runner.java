public class Runner
{
    public static Integer[] runTest()
    {
        Deque<Integer> deque = new Deque<>();
        Integer[] results = new Integer[10];
        for (int i = 0; i < 10; i++) {
            deque.pushLeft(i);
        }

        for (int i = 0; i < 10; i++) {
            deque.pushLeft(deque.popRight());
        }
        for (int i = 0; i < 10; i++) {
            results[i] = deque.popRight();
        }
        return results;
    }

    public static void main(String[] args)
    {
        Integer[] returnVal = runTest();
    }
}
