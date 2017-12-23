package to.us.tommyser.superlist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WordList {

	private ArrayList<String> wList;
	
	public WordList() {
		wList = new ArrayList<String>();
	}
	
	public void add(String str) {
		wList.add(str);
		Collections.sort(wList);
	}
	
	public void load(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			
			wList.clear();
			while(sc.hasNextLine()) {
				wList.add(sc.nextLine());
			}
			
			Collections.sort(wList);
			
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + fileName + " not found.");
		}
	}
	
	public void save(String fileName) {
		try {
			PrintWriter pw = new PrintWriter(new File(fileName));
			
			for(String str: wList) {
				pw.println(str);
			}
			
			pw.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + fileName + " not found.");
		}
	}
	
	public String[] getFilteredList(String pattern, boolean exactMatch) {
		ArrayList<String> rt = new ArrayList<String>();
		for(String str: wList)
			if(matchesFilter(str, pattern, exactMatch))
				rt.add(str);
		return rt.toArray(new String[rt.size()]);
	}
	
	public static boolean matchesFilter(String str, String patStr, boolean exactMatch) {
		str = str.replaceAll("\\s+", "");
		patStr = patStr.replaceAll("\\s+", "");

		if(!exactMatch)
			patStr = patStr + ".*";
		
		return Pattern.matches(patStr, str);
	}
	
}
