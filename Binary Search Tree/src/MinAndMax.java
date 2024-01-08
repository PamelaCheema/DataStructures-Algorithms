public class MinAndMax<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;
    }

    Node root;

    public TKey min() {
        // ADD YOUR CODE HERE.
        if(root != null)
        {
            System.out.print(min(root));
            return min(root);
        }
        return null;
    }

    private TKey min(Node node) {
        // ADD YOUR CODE HERE.
        if(node.left == null)
        {
            return node.key;
        }else{
            return min(node.left);
        }
    }

    public TKey max() {
        // ADD YOUR CODE HERE.
        if(root != null)
        {
            return max(root);
        }
        return null;
    }

    private TKey max(Node node) {
        // ADD YOUR CODE HERE.
        if(node.right == null)
        {
            return node.key;
        }else{
            return max(node.right);
        }
    }

    // This is used by our test code. Do not change.
    Node newNode(TKey key, TValue value, Node left, Node right) {
        Node node = new Node();
        node.key = key;
        node.value = value;
        node.left = left;
        node.right = right;
        return node;
    }
}
