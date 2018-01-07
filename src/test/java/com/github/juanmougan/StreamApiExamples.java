package com.github.juanmougan;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamApiExamples {

	@Test
	public void test() {
		List<String> mylist = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		List <String> actual = new ArrayList<>();

		mylist
			.stream()
			.filter(s -> s.startsWith("c"))
			.map(String::toUpperCase)
			.sorted()
			.forEach(s -> actual.add(incrementNumber(s)));
		
		List<String> expected = Arrays.asList("C2", "C3");
		assertThat(actual, is(expected));
	}
	
	private String incrementNumber(String input) {
		int number = Integer.parseInt(String.valueOf(input.charAt(1)));
		return input.charAt(0) + String.valueOf(number + 1);
	}
	
	@Test
	public void testAnagrams() {
		String s1, s2;
		
		s1 = "TestTest";
		s2 = "testsest";
		assertFalse(this.isAnagram(s1, s2));
	}
	
	private boolean isAnagram(String a, String b) {
        Map<Character, Integer> aFrequencies = initializeAlphabetMap();
        Map<Character, Integer> bFrequencies = initializeAlphabetMap();
        a.chars().map(Character::toUpperCase).forEach(c -> aFrequencies.put( ((char) c), ((int) aFrequencies.getOrDefault(((char)c), 0)) + 1 ));
        b.chars().map(Character::toUpperCase).forEach(c -> bFrequencies.put( ((char) c), ((int) bFrequencies.getOrDefault(((char)c), 0)) + 1 ));
        return aFrequencies.equals(bFrequencies);
    }
	
	private Map<Character, Integer> initializeAlphabetMap() {
		Map<Character, Integer> map = new HashMap<>();
		for (char ch = 'A'; ch <= 'Z'; ++ch)
			map.put(ch, 0);
		return map;
	}

	@Test
	public void testIntStream() {
		Stream<Integer> intStream = Stream.of(1,2,3,4);
		Stream<String> strStream = intStream.map(i -> String.valueOf(i) + " ");
		String out = strStream.collect(Collectors.joining());
		// Need to reopen Stream
		intStream = Stream.of(1,2,3,4);
		System.out.println(out);
		intStream.forEach(System.out::print);
	}

}
