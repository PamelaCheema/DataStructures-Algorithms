public class Insert<TKey extends Comparable<TKey>, TValue> {
    class Node {
        TKey key;
        TValue value;
        Node left;
        Node right;

        private Node(TKey key, TValue val)
        {
            this.key = key;
            this.value = val;
        }
    }

    Node root;

    public TValue insert(TKey key, TValue value) {
        // ADD YOUR CODE HERE.
        Node newNode = new Node(key, value);
        return insert(root, newNode);
    }

    private TValue insert(Node current, Node newNode) {
        // ADD YOUR CODE HERE.\


        if(current == null)
        {
            root = newNode;
            return null;
        }

        if(newNode.key.compareTo(current.key) < 0)
        {
            if(current.left != null)
            {
                return insert(current.left, newNode);
            }
            current.left = newNode;
            return null;
        }else
        if(newNode.key.compareTo(current.key) > 0)
        {
            if(current.right != null)
            {
                return insert(current.right, newNode);
            }
            current.right = newNode;
            return null;
        }

        TValue oldVal = current.value;
        current.value = newNode.value;
        return oldVal;
    }
}