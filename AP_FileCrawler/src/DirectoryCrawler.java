import java.io.File;


import java.util.ArrayList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class DirectoryCrawler extends Thread {

	static Multimap<String, String> DirMap  =  ArrayListMultimap.create();;
	String dir;
	
	ArrayList<textFileCrawler> txters = new ArrayList<textFileCrawler>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public DirectoryCrawler(String input) {
		// TODO Auto-generated constructor stub
		dir=input;
	}
	
	
	@Override
	public void run() {
		ScanDirectories(new File(dir));
		for (textFileCrawler elem : txters) {
			try {
				elem.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void ScanDirectories(File current){
	
		if(!current.isDirectory()){
			return;
		}
		if (!current.isHidden() && current.canRead()){						
			
			File[] subFiles = current.listFiles();
			for (File file : subFiles) {
				String filename = file.getName();
				if(filename.startsWith(".")){
					continue;	//ignore system meta files
				}
				int ext = filename.lastIndexOf(".");
				
				if(ext != -1){
					String fileExtension = filename.substring(ext+1).toLowerCase();
					if(fileExtension.compareToIgnoreCase("txt")==0  ){
						textFileCrawler crawl = new textFileCrawler(file);
						txters.add(crawl);
						crawl.start();
					}
				}
				DirMap.put(filename.toLowerCase(), file.getAbsolutePath());
				System.out.println("Included :\t"+filename.toLowerCase());
				ScanDirectories(file);
			}
		}
	}
	

}
