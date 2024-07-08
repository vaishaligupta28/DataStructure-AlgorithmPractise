## Sorting Techniques

#### Types
Can be categorised into Non-comparing sorts and comparison sorts.

Comparison sorts are:
1. Selection Sort
2. Bubble Sort
3. Insertion Sort
4. Quick Sort
5. Merge Sort
6. Heap Sort

Non-Comparison sorts are:
1. Counting Sort
2. Radix Sort
3. Bucket Sort

  

### Selection Sort
Selecting the minimum of the set in each iteration
and then swapping that minimum position with the current index position.

Approach:
- Gets minimum of the set
- Swaps minimum with the current index

For e.g. 

Unsorted Set = {9   7    8   6   4   5   2   3}  
Sorted Set = {2   3    4   5   6   7   8    9}

#### Steps:
    
````

Starting from the unsorted set={9 7 8 6 4 5 2 3}

After the 1st iteration, min:2 swapped 9 with 2, Modified Set = {2 7 8 6 4 5 9 3}          
After the 2nd iteration, min:3 swapped 7 with 3, Modified Set = {2 3 8 6 4 5 9 7}
After the 3rd iteration, min:4 swapped 8 with 4, Modified Set = {2 3 4 6 8 5 9 7}
After the 4th iteration, min:5 swapped 6 with 5, Modified Set = {2 3 4 5 8 6 9 7}
After the 5th iteration, min:6 swapped 8 with 6, Modified Set = {2 3 4 5 6 8 9 7}
After the 6th iteration, min:7 swapped 8 with 7, Modified Set = {2 3 4 5 6 7 9 8}
After the 7th iteration, min:8 swapped 9 with 8, Modified Set = {2 3 4 5 6 7 8 9} // sorted

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````

### Bubble Sort
It involves repeatedly swapping adjacent elements in either ascending or descending order, 
based on a comparison between each pair of consecutive elements. 
If elements are out of order (i.e., the larger element precedes the smaller one in ascending order), 
they are swapped to correct their positions.

With each iteration the max element reaches at the end of the list.
Which means in next iterations, we now have to sort the list till 1 element before the end of the list.

For e.g.

Unsorted Set = {9   7    8   6   4   5   2   3}  
Sorted Set = {2   3    4   5   6   7   8    9}

#### Steps:

````

Starting from the unsorted set={9 7 8 6 4 5 2 3}

After the 1st iteration, [i=0 to i=7]
           1st swapping => 9 with 7, Modified Set = {7 9 8 6 4 5 2 3}
           2nd swapping => 9 with 8, Modified Set = {7 8 9 6 4 5 2 3}
           3rd swapping => 9 with 6, Modified Set = {7 8 6 9 4 5 2 3}
           4th swapping => 9 with 4, Modified Set = {7 8 6 4 9 5 2 3}     
           5th swapping => 9 with 5, Modified Set = {7 8 6 4 5 9 2 3}     
           6th swapping => 9 with 2, Modified Set = {7 8 6 4 5 2 9 3}       
           7th swapping => 9 with 3, Modified Set = {7 8 6 4 5 2 3 9}   // pushed 9 at the end
After the 2nd iteration, [i=0 to i=6] 
           No swapping b/w 7 & 8,    Modified Set = {7 8 6 4 5 2 3 9}
           1st swapping => 8 with 6, Modified Set = {7 6 8 4 5 2 3 9}
           2nd swapping => 8 with 4, Modified Set = {7 6 4 8 5 2 3 9}
           3rd swapping => 8 with 5, Modified Set = {7 6 4 5 8 2 3 9}
           4th swapping => 8 with 2, Modified Set = {7 6 4 5 2 8 3 9}   
           5th swapping => 8 with 3, Modified Set = {7 6 4 5 2 3 8 9}   // pushed 8 at the end

and so on... till 7th iteration.

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````

### Insertion Sort
Takes an element and places it in the correct order.
Instead of trying to sort the entire elements all at once in each iteration and waste a lot of the swaps. [like in Bubble Sort]
Here, we will try to save on that many swapping operations by only doing with smaller sets each time.

Say, we first take only 1 element in the Set, now it is already sorted since there is just 1 element.
Now we will take first 2 elements, and see if all elements are in correct order. 
        If not swap the elements in this Set till we have them in correct order.
Now we will take first 3 elements, and see if all elements are in correct order.

We will keep repeating the process each time adding one more element in the Set.

In this approach we don't visit all element in every iteration but only few elements.  

For e.g.

Unsorted Set = {9   7    8   6   4   5   2   3}  
Sorted Set = {2   3    4   5   6   7   8    9}

#### Steps:

````

Starting from the unsorted set={9 7 8 6 4 5 2 3}

In the 1st iteration, start with only 1 element in partial set & consider this as a sorted sub array.
            Partial Set={9}, Unsorted sub array: {7 8 6 4 5 2 3}
                    No swapping needed, Modified Set = {9 7 8 6 4 5 2 3}
                    
In the 2nd iteration, adding 1 element i.e. 7 from unsorted sub array to partial set & and sort these if necessary.
            Partial Set={9, 7}, Unsorted sub array: {8 6 4 5 2 3}
                    1st swapping => 9 with 7, Modified Set = {7 9 8 6 4 5 2 3}
                    
In the 3rd iteration, adding 1 element i.e. 8 from unsorted sub array to partial set & sort these if necessary.
            Partial Set={7, 9, 8}, Unsorted sub array: {6 4 5 2 3}
                    1st swapping => 9 with 8, Modified Set = {7 8 9 6 4 5 2 3} 
                    
In the 4th iteration, adding 1 element i.e. 6 from unsorted sub array to partial set & sort these if necessary.
            Partial Set={7 8 9 6}, Unsorted sub array: {4 5 2 3}
                    1st swapping => 9 with 6, Modified Set = {7 8 6 9 4 5 2 3} 
                    2nd swapping => 8 with 6, Modified Set = {7 6 8 9 4 5 2 3} 
                    3rd swapping => 7 with 6, Modified Set = {6 7 8 9 4 5 2 3} 

and so on... till 7th iteration with all 7 elements in Set.

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````

### Quick Sort

### Merge Sort

### Heap Sort

### Counting Sort

### Radix Sort

### Bucket Sort


