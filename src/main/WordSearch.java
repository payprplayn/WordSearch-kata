package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordSearch {

	private int size;
	private String[] words;
	private boolean isSolved;
	private List<String> solution;
	char [][] puzzle;
	

	public WordSearch(String file) throws FileNotFoundException {
		Scanner input=new Scanner(new File(file));
		words=input.nextLine().split(",");
		String firstLine=input.nextLine().replaceAll(",", "");
		size=firstLine.length();
		puzzle = new char [size][size];
		
		/*
		 * add every letter's coordinates to puzzle
		 */
		for (int x=0; x<size; x++){
			puzzle[x][0]=firstLine.charAt(x);
		}
		for (int y=1; y<size; y++){
			if (!input.hasNextLine()) break;
			String line=input.nextLine();
			for (int x=0; x<size; x++){
				puzzle[x][y]=line.charAt(2*x);
			}
		}
		
		input.close();
		
	}

	public void solve() {
		// if it's already solved, don't solve it again.
		if (isSolved)return;
		solution=new ArrayList<String>(words.length);
		
		
		//for each word in the list...
		for (String word : words){
			StringBuilder output= new StringBuilder(word+":");
			
			
			search:
			//look through the puzzle...
			for (int x=0; x<size; x++){
				for (int y=0; y<size; y++){
				
					//check for the word horizontally forward
					if (x+word.length()<=size){
						boolean found=true;
						for (int i=0;i<word.length();i++){
							if (puzzle[x+i][y]!=word.charAt(i)){
								found=false;
								break;
							}
						}
						//if you find it, add its location to the solution and stop looking for it.
						if (found) {
							for (int i=0;i<word.length();i++){
								output.append((i==0?" ":",")+"("+(x+i)+","+y+")");
							}
							solution.add(output.toString());
							break search;
						}
					}
				}
			}
			
		}
		
		return;
	}

	public int getSize() {
		return size;
	}

	public String [] getWords() {
		return words;
	}
	
	

	public List<String> getSolution() {
		if (!isSolved)solve();
		return solution;
	}

}
