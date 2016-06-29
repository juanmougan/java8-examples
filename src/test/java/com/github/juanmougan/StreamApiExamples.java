package com.github.juanmougan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

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
			.forEach(s -> incrementNumberAndAddToList(s, actual));
		
		List<String> expected = Arrays.asList("C2", "C3");
		assertThat(actual, is(expected));
	}
	
	private void incrementNumberAndAddToList(String input, List<String> targetList) {
		int number = Integer.parseInt(String.valueOf(input.charAt(1)));
		targetList.add(input.charAt(0) + String.valueOf(number + 1));
	}

}
