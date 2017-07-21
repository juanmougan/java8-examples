package com.github.juanmougan;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class NewTest {

	@Test
	public void test() {
		int n = 100;
		for (int i = 1; i <= n; i++) {
			if (bothMultiples(n)) {
				System.out.println("FizzBuzz");
			} else {
				if (multipleOfThree(n)) {
					System.out.println("Fizz");
				} else {
					if (multipleOfFive(n)) {
						System.out.println("Buzz");
					} else {
						System.out.println(n);
					}
				}
			}
		}
	}
	
	private boolean multipleOfFive(int n) {
		return n % 5 == 0;
	}

	private static boolean multipleOfThree(int n) {
		return n % 3 == 0;
	}

	private static boolean bothMultiples(int n) {
		return (n % 5 == 0) && (n % 3 == 0);
	}
	
	@Test
	public void anotherTest() {
		int[] arr = {2, 3, 0, 1, -100, 4};
		int currentMin = 1001;	// Constrainted to 10^3
		for (int i : arr) {
			if (i < currentMin) {
				currentMin = i;
			}
		}
		System.out.println(currentMin);
	}
	
	@Test
	public void copyTest() {
		int[] oldArray = {2, 3, 0, 1, -100, 4};
		int[] newArray = Arrays.copyOfRange(oldArray, 1, oldArray.length);
		System.out.println(Arrays.toString(newArray));
	}
	
	static int[] reduceArrayByLength(int[] lengthsOnly, int cutLength) {
		List<Integer> reducedWithResult = new ArrayList<>();
		int sticksCut = 0;
		for (Integer eachLength : lengthsOnly) {
			if (canCut(eachLength, cutLength)) {
				reducedWithResult.add(cutBy(eachLength, cutLength));
				sticksCut++;
			} else {
				reducedWithResult.add(eachLength);
			}
		}
		// Add the processed sticks
		reducedWithResult.add(sticksCut);
		return toIntArray(reducedWithResult);
	}
	
	static int[] toIntArray(List<Integer> list)  {
	    int[] ret = new int[list.size()];
	    int i = 0;
	    for (Integer e : list)  
	        ret[i++] = e.intValue();
	    return ret;
	}
	
	static int cutBy(int stickLength, int cutBy) {
        return stickLength - cutBy;
    }
	
	static boolean canCut(int stickLength, int cutBy) {
        return stickLength > 0 && stickLength - cutBy >= 0;
    }

}
