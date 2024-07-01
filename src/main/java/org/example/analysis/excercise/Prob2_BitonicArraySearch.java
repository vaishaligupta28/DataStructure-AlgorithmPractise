package org.example.analysis.excercise;

/**
 * Problem Statement 2: Search in a bitonic array
 *
 * An array is bitonic if it comprises an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers.
 *
 * Write a program that, given a bitonic array of 𝑛 distinct integer values,
 * determines whether a given integer is in the array.
 *
 * Standard version: Use ∼3logN compares in the worst case.
 *
 *
 * Solution Summary :
 *
 * Since any bitonic array has two sorted arrays and it is joined by a bitonic intersection point.
 * If we find the bitonic intersection point, we can traverse over and perform binary search in
 * both the first and second sorted array separately to find the target value.
 *
 *
 * Time Complexity : O(3logN)
 * - Finding intersection point - O(logN)
 * - Search in the first half [ascending sequence] - O(logN)
 * - Search in the second half [descending sequence] - O(logN)
 */
public class Prob2_BitonicArraySearch {

    public static void main(String[] args) throws Exception {
        int[] bitonicArray = {3,4,6,8,7,6,5,2,1,0};
        Prob2_BitonicArraySearch s = new Prob2_BitonicArraySearch();

        int searchValue = 1;
        // Time Complexity : O(3logN) for all 3 operations

        // Operation1: logN
        int intersectionPointIndex = s.binarySearchIntersection(bitonicArray);
        System.out.println("Intersection Point Index[Where pattern changes] = " + intersectionPointIndex);

        // Operation2: logN
        System.out.println("Searching in first half: increasing sequence of integers");
        boolean found = s.leftSectionSearch(bitonicArray, searchValue,
                0, intersectionPointIndex);
        if(!found) {
            // Operation3: logN
            System.out.println("Searching in second half: decreasing sequence of integers");
            found = s.rightSectionSearch(bitonicArray, searchValue,
                    intersectionPointIndex+1, bitonicArray.length);
        }
        System.out.println("found = " + found);


        // For Time Complexity : O(2logN) check the optimised version
        //Prob2_BitonicSearchOptimised class
    }

    private boolean leftSectionSearch(int[] searchArray, final int searchValue,
                                    int startIndex, int endIndex) {
        int start = startIndex;
        int end = endIndex;
        boolean found = false;
        while (start < end) {
            int mid = (start + end -1)/2;

            System.out.println("AscendingBinarySearch:: left=["+ start+ "]" +",right=["+ end+"]");
            if(searchValue == searchArray[mid]) {
                found = true;
                break;
            }

            if(searchValue > searchArray[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return found;
    }


    private boolean rightSectionSearch(int[] searchArray, final int searchValue,
                                    int startIndex, int endIndex) {
        int start = startIndex;
        int end = endIndex;
        boolean found = false;
        while (start < end) {
            int mid = (start + end -1)/2;

            System.out.println("DescendingBinarySearch:: left=["+ start+ "]" +",right=["+ end+"]");
            if(searchValue == searchArray[mid]) {
                found = true;
                break;
            }

            if(searchValue < searchArray[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return found;
    }


    private int binarySearchIntersection(int[] searchArray) {
        int start = 0;
        int end = searchArray.length;
        int intersectionPointIndex = 0;
        while (start <= end) {
            int mid = (start + end -1)/2;

            System.out.println("BitonicArray:: left=["+ start+ "]" +",right=["+ end+"]");
            // this is the intersection point
            if((searchArray[mid-1] < searchArray[mid]) &&
                    (searchArray[mid] > searchArray[mid+1])) {
                intersectionPointIndex = mid;
                break;
            }

            if((searchArray[mid-1] < searchArray[mid]) &&
                    (searchArray[mid] < searchArray[mid+1])) {
                start = mid + 1; //got to right section
            } else {
                end = mid; // go to left section
            }
        }
        return intersectionPointIndex;
    }
}
