import java.util.ArrayList;
import java.util.List;

public class AdjacencyListGraph {
    private int V;  // Number of vertices.
    private int E;  // Number of edges.
    private List<Integer>[] edges;

    /** Initializes a graph with {@code V} vertices. */
    public AdjacencyListGraph(int V) {
        // ADD YOUR CODE HERE.
        if(V < 0)
        {
            throw new IllegalArgumentException();
        }

        edges = new List[V];

        this.V = V;
        this.E = 0;
    }

    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        // ADD YOUR CODE HERE.

        if(v >= 0 && v < V && w >= 0 && w < V)
        {
            if(v == w)
            {
                return;
            }
            if(edges[v] == null)
            {
                edges[v] = new ArrayList<>();
                //edges[v].add(w);
            }
            if(edges[w] == null)
            {
                edges[w] = new ArrayList<>();
               // edges[w].add(v);
            }

            edges[v].add(w);
            edges[w].add(v);

        }else{

            throw new IllegalArgumentException();

        }
    }

    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        // ADD YOUR CODE HERE.

        if(v >= 0 && v < V)
        {

        }else{

            throw new IllegalArgumentException();
        }

        return null;
    }

    /** Returns the number of edges in this graph. */
    public int E() {
        // ADD YOUR CODE HERE.
        return E;
    }

    /** Returns the number of vertices in this graph. */
    public int V() {
        // ADD YOUR CODE HERE.
        return V;
    }
}
