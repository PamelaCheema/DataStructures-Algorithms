public class Insert_Runner
{

    public static void main(String[] args)
    {
        Insert<Integer, String> tree = new Insert<Integer, String>();
        tree.insert(8,  "Margaret");
        tree.insert(4,  "Ferdinand");
        System.out.println("Old value was: " + tree.insert(12, "Rachel"));
        tree.insert(2,  "Blanca");
        tree.insert(6,  "Joana");
        tree.insert(10, "Paul");
        tree.insert(14, "Victor");
        System.out.println("Old value was: " + tree.insert(12, "Randy"));
        System.out.println(Insert_TestHelper.levelOrder(tree));
    }
}