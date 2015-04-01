import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.google.common.collect.Multimap;



public class CrawlEngine {

	static Multimap<String, String> filesAndFolders = DirectoryCrawler.DirMap;
	static Multimap<String, String> textKeyWords = textFileCrawler.keyWordMap;



	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		CrawlEngine crawler = new CrawlEngine();
		crawler.index();
		byte input[] = new byte[100];

		while(true)	{
			System.out.print("\nPlease enter the file name to search for : \t");
			System.in.read(input);
			System.out.println("Search For : " + new String(input).trim());
			crawler.displayResults(crawler.search(new String(input).trim()));
		}		
	}

	public void index(){
		DirectoryCrawler crawler[] =  {new DirectoryCrawler("E:\\7Diagnols"),new DirectoryCrawler("E:\\Jobs")};
		System.out.println("Indexeing Locations, Please wait");
		for (DirectoryCrawler cr : crawler) {
			cr.start();
		}
		for (DirectoryCrawler cr : crawler) {
			try {
				cr.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Indexing Complete!");
	}

	public String[][] search(String name){

		String searchFor = name.toLowerCase();
		System.out.println("Search For:\t"+name);
		ArrayList<String> files = new ArrayList<String>();
		ArrayList<String> keyWords = new ArrayList<String>();
		if(filesAndFolders.containsKey(searchFor)){	//Look for name in the file name index
			files.addAll(filesAndFolders.get(searchFor));
		}

		if(textKeyWords.containsKey(searchFor)){		//look for name in text file index
			keyWords.addAll(textKeyWords.get(searchFor));
		}

		//removing duplicate entries in result
		String [] C = new HashSet<String>(Arrays.asList(files.toArray(new String[files.size()]))).toArray(new String[0]);
		String [] D = new HashSet<String>(Arrays.asList(keyWords.toArray(new String[keyWords.size()]))).toArray(new String[0]);

		//	String[][] results ={ C/*files.toArray(new String[files.size()]), keyWords.toArray(new String[keyWords.size()])/*};
		String[][] results ={ C, D};
		return results;


	}
	public void displayResults(String[][] res){
		String[] files = res[0];
		String[] keyWords = res[1];

		System.out.println("\nFound "+(files.length +keyWords.length) +" Results!");
		if(files.length != 0){
			System.out.println("Found "+ files.length + " match in file/folder names :\n");
			int i=0;
			for (String path : files) {
				System.out.println("\t("+ (++i)+") -\t"+path);
			}
		}

		if(keyWords.length != 0){
			System.out.println("Found "+ keyWords.length + " match inside text files:\n");
			int i=0;
			for (String path : keyWords) {
				System.out.println("\t("+ (++i)+") -\t"+path);
			}
		}

	}





}
