public class Runner {
    public static void main(String[] args)
    {
        BFSWebCrawler webCrawler = new BFSWebCrawler();
        webCrawler.crawl("https://tesla.lwsd.org");
    }
}