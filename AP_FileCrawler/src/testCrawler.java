import static org.junit.Assert.*;

import org.junit.Test;


public class testCrawler {

	@Test
	public void testkeyWord() {
		CrawlEngine test = new CrawlEngine();
		test.index();
		String[][] result = test.search("fire60");

		if(result[1].length == 0){
			fail("failed to search");
		}
	}
	@Test
	public void testFileName() {
		CrawlEngine test = new CrawlEngine();
		test.index();
		String[][] result = test.search("BitTorrent.exe");

		if(result[0].length == 0){
			fail("failed to search");
		}
	}
}
