package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {

	private int size;

	public WordSearch(String file) throws FileNotFoundException {
		Scanner input=new Scanner(new File(file));
		input.nextLine();
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

}
