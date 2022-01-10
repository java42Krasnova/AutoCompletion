import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoComplectionImplTest {
	private static final int COUNT = 100000;
	
	AutoCompletion autoCompl;
	String[] arrStr = { "first word", "new second word", "newword", "third word", "thirdword", "newBird", "sec",
			"wordok", "ok", "new" };
	List<String> listStr = Arrays.asList(arrStr);

	@BeforeEach
	void setUp() throws Exception {
		autoCompl = new AutoComplectionImpl();
		fill(autoCompl);
	}

	private void fill(AutoCompletion autoCompl) {
		for (String str : listStr) {
			autoCompl.addWord(str);
		}
	}

	@Test
	void testAddWord() {
		assertTrue(autoCompl.addWord("word"));
		//AutoCompletion in this case based on hashSet
		assertFalse(autoCompl.addWord("word"));
	}

	@Test
	void testRemoveWord() {
		AutoCompletion autoComplEmpty = new AutoComplectionImpl();
		//removing word from empty AutoComplection
		assertFalse(autoComplEmpty.removeWord("word"));
		//removing word
		assertTrue(autoCompl.removeWord("thirdword"));
		//removing word not from collection
		assertFalse(autoCompl.removeWord("thirdword"));
	}

	@Test
	void testGetCompletionOptions() {
		Iterable<String> iter = autoCompl.getCompletionOptions("word");
		iter.forEach(str -> assertTrue(str.startsWith("word")));
		Iterable<String> iterEmpty = autoCompl.getCompletionOptions("99");
		List<String> empty = new ArrayList<>();
		iterEmpty.forEach(empty::add);
		assertEquals(0, empty.size());
		//
		performanceTest();
	}

	private void performanceTest() {
		List<String> randomListStr =fillRandomList();
		AutoCompletion randomLinkedListStr = new AutoComplectionImpl (randomListStr );
		AutoCompletion randomTreeSetStr = new AutoComplectionImpl( new TreeSet<>(randomListStr));
		AutoCompletion randomHashSetStr = new AutoComplectionImpl( new HashSet<>(randomListStr));
		
		Instant instList = Instant.now();
		randomLinkedListStr.getCompletionOptions("a");
		System.out.println("List " + ChronoUnit.MICROS.between(instList, Instant.now())+ " MICROSECONDS");
		Instant instHash = Instant.now();
		randomHashSetStr.getCompletionOptions("a");
		System.out.println("Hash " + ChronoUnit.MICROS.between(instHash, Instant.now())+ " MICROSECONDS");
		Instant instTrInstant = Instant.now();
		randomTreeSetStr.getCompletionOptions("a");
		System.out.println( "Tree " + ChronoUnit.MICROS.between(instTrInstant, Instant.now())+ " MICROSECONDS");
	}

	private List<String> fillRandomList( ) {
		List<String> list = new LinkedList<>();
		for(int i=0; i< COUNT; i++) {
			list.add(createRandomString());	
		}
	    return list;
	}

	private String createRandomString() {
		String symbols = "abcdef";
	    char[] array = symbols.toCharArray();
	    ArrayList<String> arrStr = new ArrayList<>();
	    for (int i = 0; i < array.length; i++) {
	    	arrStr.add(String.valueOf(array[i]));
	    }
	    Collections.shuffle(arrStr);
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < 5; i++) {
	        sb.append(arrStr.get(i));
	    }
		return sb.toString();
	}
}
