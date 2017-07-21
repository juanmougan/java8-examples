package com.github.juanmougan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args ) {
        int[] input = {6, 5, 4, 4, 2, 2, 8};
        int[] output = cutSticks(input);
        System.out.println(Arrays.toString(output));
    }
    
    static int[] cutSticks(int[] lengths) {
        // Trim the lenght
        int[] lengthsOnly = Arrays.copyOfRange(lengths, 1, lengths.length);
        
        List<Integer> resultList = new ArrayList<>();
        
        while (!onlyZerosArray(lengthsOnly)) {
            // Find the cut length for this iteration
            int cutLength = findMinInArray(lengthsOnly);
            // Reduce by the minimum
            int[] reducedArrayWithResult = reduceArrayByLength(lengthsOnly, cutLength);
            // Print the value and alter the array
            // System.out.println(reducedArrayWithResult[reducedArrayWithResult.length - 1]);
            // Add the reduced value to the result list and alter the array
            resultList.add(reducedArrayWithResult[reducedArrayWithResult.length - 1]);
            lengthsOnly = Arrays.copyOfRange(reducedArrayWithResult, 0, reducedArrayWithResult.length - 1);
        }
        return toIntArray(resultList);
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
    
    static int findMinInArray(int[] arr) {
        int currentMin = 1001;	// Constrainted to 10^3
		for (int i : arr) {
			if (i > 0 && i < currentMin) {
				currentMin = i;
			}
		}
        return currentMin;
    }
    
    static boolean onlyZerosArray(int[] arr) {
        for (int i : arr) {
			if (i != 0) {
				return false;
			}
		}
        return true;
    }
    
    static int cutBy(int stickLength, int cutBy) {
        return stickLength - cutBy;
    }

    static boolean canCut(int stickLength, int cutBy) {
        return stickLength > 0 && stickLength - cutBy >= 0;
    }
    
}
