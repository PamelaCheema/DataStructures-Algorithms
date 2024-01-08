import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class DFSPathFinder {
    private boolean[] marked;
    private int[] edgeTo;
    private final int source;
    private Digraph G;

    public DFSPathFinder(Digraph G, int s) {
        if (0 <= s && s < G.V()) {
            this.G = G;
            this.source = s;
            this.marked = new boolean[G.V()];
            this.edgeTo = new int[G.V()];
            dfs(G, s);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void dfs(Digraph G, int v)
    {
        marked[v] = true;
        for(int i : G.adj(v))
        {
            if(!marked[i])
            {
                dfs(G, i);
                edgeTo[i] = v;
            }
        }
    }

    public boolean hasPathTo(int v) {
        if (0 <= v && v < this.G.V()) {
            return this.marked[v];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Iterable<Integer> pathTo(int v) {
        if (0 <= v && v < this.G.V()) {
            if (!this.hasPathTo(v)) {
                return null;
            } else {
                Stack<Integer> path = new Stack();

                for(int i = v; i != source; i = this.edgeTo[i]) {
                    path.push(i);
                }

                path.push(source);
                ArrayList<Integer> finalPath = new ArrayList<Integer>();
                Iterator<Integer> iter = path.iterator();

                while (iter.hasNext()){
                    finalPath.add(path.pop());
                }
                return finalPath;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
