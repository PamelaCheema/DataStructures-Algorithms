class Insert_TestHelper {
    static <TKey extends Comparable<TKey>, TValue> java.util.List<TValue> levelOrder(Insert<TKey, TValue> tree) {
        java.util.List<TValue> list = new java.util.ArrayList<TValue>();
        java.util.Queue<Insert<TKey, TValue>.Node> queue = new java.util.ArrayDeque<Insert<TKey, TValue>.Node>();
        if (tree.root != null) {
            queue.add(tree.root);
            while (!queue.isEmpty()) {
                Insert<TKey, TValue>.Node node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                list.add(node.value);
            }
        }
        return list;
    }
}