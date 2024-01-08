public class TestHelper {
    static <Key extends Comparable<Key>> Comparable[] getDataArray(BinaryHeapMax<Key> pq) {
        java.lang.reflect.Field contentsField = null;
        java.lang.reflect.Field[] fields = BinaryHeapMax.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().equals(Comparable[].class)) {
                contentsField = fields[i];
                break;
            }
        }
        if (contentsField == null) {
            throw new IllegalStateException("[fail] Missing Key[] field.");
        }
        contentsField.setAccessible(true);
        try {
            return (Comparable[]) contentsField.get(pq);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    static <Key extends Comparable<Key>> void validateHeapInvariant(BinaryHeapMax<Key> pq) {
        validateHeapInvariant(getDataArray(pq), pq.size());
    }

    static void validateHeapInvariant(Comparable[] data, int size) {
        if (data.length < size) {
            System.out.println("[fail] a[] field must contain at least size elements; size: " + size + "; a[] field length: " + data.length);
            return;
        }
        for (int i = 1; i < size/2; ++i) {
            int leftChildIndex = 2*i;
            int rightChildIndex = 2*i + 1;
            Comparable key = data[i];
            Comparable leftChild = data[leftChildIndex];
            Comparable rightChild = (rightChildIndex < size) ? data[rightChildIndex] : null;
            if (key == null) {
                System.out.println("[fail] Unexpected null element at index: " + i);
                continue;
            }
            if (leftChild == null) {
                System.out.println("[fail] Unexpected null element at index: " + leftChildIndex);
                continue;
            }
            if (key.compareTo(leftChild) < 0) {
                System.out.println("[fail] Parent (" + key + ") is smaller than one of its children (" + leftChild + ")");
            }
            if (rightChild != null && key.compareTo(rightChild) < 0) {
                System.out.println("[fail] Parent (" + key + ") is smaller than one of its children (" + rightChild + ")");
            }
        }
    }

    static <Key extends Comparable<Key>> boolean validateHeapOrder(BinaryHeapMax<Key> pq, Key[] expected) {
        Comparable[] actual = TestHelper.getDataArray(pq);
        if (actual.length <= expected.length) {
            System.out.println("[fail] a[] size is too small; must be at least " + (expected.length + 1) + "; actual size: " + actual.length);
            return false;
        }
        if (actual[0] != null) {
            System.out.println("[fail] a[0] must be null");
            return false;
        }
        for (int i = 1; i <= expected.length; ++i) {
            if (actual[i] == null || !actual[i].equals(expected[i-1])) {
                System.out.println("[fail] expected and actual arrays differ; "
                        + "expected: " + java.util.Arrays.toString(expected)
                        + "; actual: " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(actual, 1, pq.size() + 1)));
                return false;
            }
        }
        return true;
    }
}
