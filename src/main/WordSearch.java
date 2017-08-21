package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {

	private int size;
	private String[] words;

	public WordSearch(String file) throws FileNotFoundException {
		Scanner input=new Scanner(new File(file));
		words=input.nextLine().split(",");
		String firstLine=input.nextLine().replaceAll(",", "");
		size=firstLine.length();
		input.close();
		
	}

	public void solve() {
		// TODO Auto-generated method stub
		
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}

	public String [] getWords() {
		// TODO Auto-generated method stub
		return words;
	}

}
