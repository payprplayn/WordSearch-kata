package test;
import static org.junit.Assert.*;

import java.io.File;
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
		wordSearch1.solve();
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

}
