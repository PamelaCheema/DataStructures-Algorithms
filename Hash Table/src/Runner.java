public class Runner {
    public static void main(String[] args)
    {
        SeparateChainingHashTable<Integer, String> table = new SeparateChainingHashTable<Integer, String>(10);
        table.put(1, "foo");
        table.put(2, "bar");
        System.out.println("Return value for unknown key: " + table.get(3));
    }
}
