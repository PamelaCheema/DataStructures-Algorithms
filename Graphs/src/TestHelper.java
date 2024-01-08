import java.util.List;

public class TestHelper {
    static int[] toArray(Iterable<Integer> iterable) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int element : iterable) list.add(element);
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; ++i) result[i] = list.get(i);
        return result;
    }

    static List[] getInternalAdjacencyLists(AdjacencyListGraph graph) throws ClassNotFoundException, IllegalAccessException {
        java.lang.reflect.Field edgesField = null;
        for (java.lang.reflect.Field field : getListArrayFields()) {
            if (field.getName().equals("edges")) {
                edgesField = field;
                break;
            }
        }
        if (edgesField == null) {
            throw new IllegalStateException("Missing List[] edges field");
        }
        edgesField.setAccessible(true);
        return (List[]) edgesField.get(graph);
    }

    private static java.util.List<java.lang.reflect.Field> getListArrayFields() {
        java.util.List<java.lang.reflect.Field> arrayFields = new java.util.ArrayList<java.lang.reflect.Field>();
        java.lang.reflect.Field[] fields = AdjacencyListGraph.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().isArray() && fields[i].getType().getComponentType().equals(List.class)) {
                arrayFields.add(fields[i]);
            }
        }
        return arrayFields;
    }
}
