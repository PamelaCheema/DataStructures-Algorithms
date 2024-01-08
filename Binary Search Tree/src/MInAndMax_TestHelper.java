public class MInAndMax_TestHelper {
    static MinAndMax<String, Integer> createRootOnlyTree() {
        MinAndMax<String, Integer> tree = new MinAndMax<>();
        tree.root = tree.newNode("Margaret", 819, null /* left */, null /* right */);
        return tree;
    }

    static MinAndMax<String, Integer> createDefaultTree() {
        MinAndMax<String, Integer> tree = new MinAndMax<>();
        tree.root =
                tree.newNode("Margaret", 819,
                        tree.newNode("Ferdinand", 907,
                                tree.newNode("Blanca", 577,
                                        tree.newNode("Alvaro", 613, null /* left */, null /* right */),
                                        tree.newNode("Charlie", 696, null /* left */, null /* right */)),
                                tree.newNode("Joana", 489,
                                        tree.newNode("Harry", 518, null /* left */, null /* right */),
                                        tree.newNode("Lincoln", 719, null /* left */, null /* right */))),
                        tree.newNode("Sheri", 507,
                                tree.newNode("Paul", 402,
                                        tree.newNode("Olivia", 612, null /* left */, null /* right */),
                                        tree.newNode("Rachel", 591, null /* left */, null /* right */)),
                                tree.newNode("Victor", 631,
                                        tree.newNode("Taneka", 596, null /* left */, null /* right */),
                                        tree.newNode("Wendy", 519, null /* left */, null /* right */))));
        return tree;
    }
}
