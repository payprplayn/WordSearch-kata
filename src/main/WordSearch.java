package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class WordSearch {

	private int size;
	private String[] words;
	private List<String> solution;
	char [][] puzzle;
	private enum Orientation{HORIZONTAL,VERTICAL,ASCENDING,DESCENDING}
	

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
		solve();
		
	}

	public void solve() {
		solution=new ArrayList<String>(words.length);
		
		//words we haven't found yet
		List<String> targets=new LinkedList<String>(Arrays.asList(words));
		
		//look through the puzzle...
		for (int x=0; x<size; x++){
			for (int y=0; y<size; y++){
				ListIterator<String> iter=targets.listIterator();
				
				//for each word in the list...
				while (iter.hasNext()){
					String word=iter.next();
					
					//look for the word in each possible orientation
					for (Orientation o: Orientation.values()){
						if(search(word, o, false, x,y)){
							StringBuilder output= new StringBuilder(word+":");
							for (int i=0;i<word.length();i++){
								output.append((i==0?" ":",")+"("+(updateX(x,i,o))+","+updateY(y,i,o)+")");
							}
							solution.add(output.toString());
							iter.remove();
						}
						
						//backwards and forwards
						else if (search(word, o, true, x,y)){
							StringBuilder output= new StringBuilder(word+":");
							for (int i=word.length()-1;i>=0;i--){
								output.append((i==word.length()-1?" ":",")+"("+(updateX(x,i,o))+","+updateY(y,i,o)+")");
							}
							solution.add(output.toString());
							iter.remove();
						}
					}
					
				}
			}
			
		}
		
		return;
	}
	
	//look for a word in the given orientation and direction starting from (x,y)
	private boolean search(String word, Orientation orientation, boolean backward, int x, int y){
		//check that there's enough room for the word.
		switch (orientation){
		case HORIZONTAL:
			if (x+word.length()>size)return false;
			break;

		case VERTICAL:
			if (y+word.length()>size)return false;
			break;

		case ASCENDING:
			if (x+word.length()>size || y-word.length()<-1) return false;
			break;

		case DESCENDING:
			if (x+word.length()>size ||y+word.length()>size) return false;

		}
		
		//see if the word is there
		for (int i=backward?word.length()-1:0; backward?i>=0:i<word.length();i=backward?(i-1):(i+1)){
			if (puzzle[updateX(x,i,orientation)][updateY(y,i,orientation)]!=word.charAt(backward?word.length()-i-1:i)) return false;
		}
		return true;
		
	}
	
	//update the x coordinate to move amount spaces along the given orientation
	private int updateX(int x, int amount, Orientation orientation){
		switch (orientation){
		case ASCENDING:
		case DESCENDING:
		case HORIZONTAL:return x+amount;
		default: return x;
		}
			
	}
	
	//update the y coordinate to move amount spaces along the given orientation
	private int updateY(int y, int amount, Orientation orientation){
		switch (orientation){
		case DESCENDING:
		case VERTICAL:return y+amount;
		case ASCENDING:return y-amount;
		default: return y;
		}
			
	}

	public int getSize() {
		return size;
	}

	public String [] getWords() {
		return words;
	}
	
	

	public List<String> getSolution() {
		return solution;
	}

}
