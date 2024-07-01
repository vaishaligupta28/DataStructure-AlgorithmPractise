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
 * Signing bonus: Use ∼2logN compares in the worst case
 * (and prove that no algorithm can guarantee to perform fewer than ∼2logN compares in the worst case).
 *
 *
 *
 * Solution Summary :
 *
 * In this optimised version of the algorithm we are trying to find the bitonic intersection point while
 * simultaneously chcking for the searchValue and logically selecting the portion of the bitonic array based
 * on where the searchValue lies.
 *
 * - At the start we just find a mid of the bitonic array.
 *
 * - Now check if the mid of the bitonic array is in the increasing sequence or decreasing sequence
 * by checking it with its prev value. If prev < mid, it is in increasing sequence,
 * otherwise it is in the decreasing sequence.
 *
 * - If the mid is in increasing sequence and searchValue is greater than mid then value is found in the right sub array.
 * - If the mid is in decreasing sequence and searchValue is greater than mid then value is found in the left sub array.
 * - So in both cases we keep finding the bitonic intersection point. [bitonicSearch]
 *
 *
 * - If the mid is in increasing/ or decreasing sequence and searchValue is less than mid
 * then value can be found anywhere in the right or left sub array. Hence, we have to check in both.
 *
 * - Interesting point : When the searchValue is less than mid in such cases there is a chance that we
 * will have to perform a binary search on a bitonic array.
 *  For instance, doing an ascending binary search for the value 5 in the array [2, 4, 5, 6, 9, 8, 7]
 *  will work because 7 and 8 are bigger than the desired value 5.
 *
 *
 *  Reference:
 *  <a href="https://stackoverflow.com/a/24098821">...</a>
 *
 * Time Complexity : O(2logN)
 */
public class Prob2_BitonicSearchOptimised {

    public static void main(String[] args) {
        int[] bitonicArray = {3,4,6,8,7,6,5,2,1,0};

//        int[] bitonicArray = {2,4,5,6,9,8,1};

        Prob2_BitonicSearchOptimised s = new Prob2_BitonicSearchOptimised();


        s.bitonicSearch(bitonicArray, 0, bitonicArray.length, 1);
    }

    private void bitonicSearch(int[] array, int left, int right, int searchValue) {
        System.out.println("BitonicArray:: left=["+ left+ "]" +",right=["+ right+"]");
        int mid = (left + right)/2;

        if(searchValue == array[mid]) {
            System.out.println("value found");
            return;
        }

        // The `mid` is part of an increasing sequence [when mid is bigger than its prev value]
        if(array[mid] > array[mid-1]) {
            System.out.println("<<<Mid ["+mid+"] is found in the increasing subsequence. Max is right to this mid.>>>");

            // It means `mid` is still not the max element in the increasing sequence so we have to keep on finding the bitonic array.
            // This step halves the bitonic array and goes to the second half.[right half]
            // This step also says that the searchValue is found in second half itself.
            if(searchValue > array[mid]) {
                bitonicSearch(array, mid+1, right, searchValue);
            } else {
                // It means the searchValue can be present in either left or right subArray so check in both
                ascendingBinarySearch(array, left, mid, searchValue);
                descendingBinarySearch(array, mid+1, right, searchValue);
            }
        }

        // The `mid` is part of a decreasing sequence [when mid is smaller than its prev value]
        else {
            System.out.println("<<<Mid ["+mid+"] is found in the decreasing subsequence. Max is left to this mid.>>>");

            // It means `mid` is still not the max element in the decreasing sequence so we have to keep on finding the bitonic array.
            // This step halves the bitonic array and goes to the first half. [left half]
            // This step also says that the searchValue is found in first half itself.
            if(searchValue > array[mid]) {
                bitonicSearch(array, left, mid, searchValue);
            } else {

                // It means the searchValue can be present in either left or right subArray so check in both
                ascendingBinarySearch(array, left, mid, searchValue);
                descendingBinarySearch(array, mid+1, right, searchValue);
            }
        }
    }


    private void ascendingBinarySearch(int[] array, int left, int right, int searchValue) {
        if(left == right) return;

        System.out.println("AscendingBinarySearch:: left=["+ left+ "]" +",right=["+ right+"]");

        int mid = (left+right)/2;

        if(searchValue == array[mid]) {
            System.out.println("value found");
            return;
        }

        if(searchValue < array[mid]) {
            ascendingBinarySearch(array, left, mid, searchValue);
        } else {
            ascendingBinarySearch(array, mid+1, right, searchValue);
        }
    }



    private void descendingBinarySearch(int[] array, int left, int right, int searchValue) {
        if(left == right) return;

        System.out.println("DescendingBinarySearch:: left=["+ left+ "]" +",right=["+ right+"]");
        int mid = (left + right) / 2;

        if(searchValue == array[mid]) {
            System.out.println("value found");
            return;
        }

        if(searchValue > array[mid]) {
            descendingBinarySearch(array, left, mid, searchValue);
        } else {
            descendingBinarySearch(array, mid+1, right, searchValue);
        }
    }
}