import java.util.ArrayList;
import java.util.List;

public class Digraph {
    private int V;
    private int E;
    private List<Integer>[] edges;

    public Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.edges = (List<Integer>[]) new List[V];
    }

    /** Adds the direct edge v→w to this graph. */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (edges[v] == null) {
            edges[v] = new ArrayList<Integer>();
        }
        edges[v].add(w);
        E++;
    }

    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        if (edges[v] == null) {
            edges[v] = new ArrayList<Integer>();
        }
        return edges[v];
    }

    /** Returns the number of edges in this graph. */
    public int E() {
        return E;
    }

    /** Returns the number of vertices in this graph. */
    public int V() {
        return V;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}
