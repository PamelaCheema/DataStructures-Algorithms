public class MInAndMax_Runner {
    public static String runTest()
    {
        MinAndMax<String, Integer> tree = MInAndMax_TestHelper.createDefaultTree();
        String min = tree.min();
        return min != null ? min.toString() : "Not found";
    }

    public static void main(String[] args)
    {
        String returnVal = runTest();
    }
}
