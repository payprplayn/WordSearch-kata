package test;
import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import main.WordSearch;

public class WordSearchTest {
	WordSearch wordSearch1;
	
	@Before
	public void setUp(){
		try {
			wordSearch1=new WordSearch("wordsearch1.txt");
		} catch (FileNotFoundException e) {
			fail("file not found");
		}
	}

	@Test
	public void wordSearchCorrectlyDeterminesPuzzleSize() {
		assertEquals(wordSearch1.getSize(),15);
	}
	
	@Test
	public void wordSearchCorrectlyBuildsWordList() {
		assert Arrays.asList(wordSearch1.getWords()).contains("SCOTTY");
		assert Arrays.asList(wordSearch1.getWords()).contains("BONES");
		assert Arrays.asList(wordSearch1.getWords()).contains("UHURA");
	}
	
	@Test
	public void wordSearchFindsForwardWords(){
		assert wordSearch1.getSolution().contains("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)");
	}
	
	@Test
	public void wordSearchFindsBackwardWords(){
		assert wordSearch1.getSolution().contains("KIRK: (4,7),(3,7),(2,7),(1,7)");
	}
	
	@Test
	public void wordSearchFindsDownwardsWords(){
		assert wordSearch1.getSolution().contains("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)");
	}
	@Test
	public void wordSearchFindsUpwardsWords(){
		assert wordSearch1.getSolution().contains("KHAN: (5,9),(5,8),(5,7),(5,6)");
	}
	@Test
	public void wordSearchFindsDownRightWords(){
		assert wordSearch1.getSolution().contains("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)");
	}
	@Test
	public void wordSearchFindsDownLeftWords(){
		assert wordSearch1.getSolution().contains("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)");
	}
	@Test
	public void wordSearchFindsUpLeftWords(){
		assert wordSearch1.getSolution().contains("SULU: (3,3),(2,2),(1,1),(0,0)");
	}
	@Test
	public void wordSearchFindsUpRightWords(){
		assert wordSearch1.getSolution().contains("ENTERPRISE: (5,14),(6,13),(7,12),(8,11),(9,10),(10,9),(11,8),(12,7),(13,6),(14,5)");
	}
}
