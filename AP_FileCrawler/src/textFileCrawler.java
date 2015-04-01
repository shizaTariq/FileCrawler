import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class textFileCrawler extends Thread {


	static Multimap<String, String> keyWordMap =  ArrayListMultimap.create();
	File toCrawl;
	Scanner lineScanner;
	Scanner wordScanner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public textFileCrawler(File input) {
		// TODO Auto-generated constructor stub
		toCrawl=input;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	System.out.println("Scanning text file-\t"+this.toCrawl.getName());
		lineScanner = null;
		if(this.toCrawl.canRead()){


			try {

				lineScanner = new Scanner(this.toCrawl);
				while (lineScanner.hasNextLine()) {
					wordScanner = new Scanner(lineScanner.nextLine());
					while (wordScanner.hasNext()) {
						String s = wordScanner.next();
						if(s.length() >3){
							keyWordMap.put(s.toLowerCase(), toCrawl.getAbsolutePath());
							System.out.println("KeyWord Included :\t"+s+"\t("+toCrawl.getAbsolutePath()+")");
						}
					}
				}
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
