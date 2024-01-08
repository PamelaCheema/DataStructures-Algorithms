import java.util.*;

public class BFSWebCrawler {
    /**
     * Crawls the known web and outputs a message for each URL along with its distance from the root.
     */
    private boolean[] marked;
    private int[] edgesTo;
    private String root;

    public void crawl(String root) {
        // ADD YOUR CODE HERE.
        List<String> urls = getReferencedUrls(root);
        marked = new boolean[urls.size()];
        edgesTo = new int[urls.size()];
        this.root = root;
        /**for(int i = 0; i < urls.size(); i++)
         {
         System.out.println(urls.get(i));
         }**/

    }

    private void bfs(List<String> urls, String root) {
      Queue<String> path = new LinkedList<String>();
      path.add(root);
      Set<String> marked = new HashSet<String>();
      marked.add(root);

    }

    /**
     * Fetches the list of referenced (linked) URLs from the given URL. DO NOT CHANGE.
     */
    private List<String> getReferencedUrls(String url) {
        return WebData.getUrlMappings(url);
    }
}