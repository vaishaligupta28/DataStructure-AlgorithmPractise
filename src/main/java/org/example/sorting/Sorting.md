## Selection Sort
Selecting the minimum of the set in each iteration
and then swapping that minimum position with the current's index position.

Approach:
- Gets minimum of the set
- Swaps minimum with the current index

For e.g. 

Unsorted Set = {9   7    8   6   4   5   2   3}  
Sorted Set = {2   3    4   5   6   7   8    9}

#### Steps:
    
````

Starting from the unsorted set={9 7 8 6 4 5 2 3}

After the 1st iteration, swapped 9 with 2, Sorted Set = {2 7 8 6 4 5 9 3}          
After the 2nd iteration, swapped 7 with 3, Sorted Set = {2 3 8 6 4 5 9 7}
After the 3rd iteration, swapped 8 with 4, Sorted Set = {2 3 4 6 8 5 9 7}
After the 4th iteration, swapped 6 with 5, Sorted Set = {2 3 4 5 8 6 9 7}
After the 5th iteration, swapped 6 with 5, Sorted Set = {2 3 4 5 6 8 9 7}
After the 6th iteration, swapped 6 with 5, Sorted Set = {2 3 4 5 6 7 9 8}
After the 7th iteration, swapped 6 with 5, Sorted Set = {2 3 4 5 6 7 8 9}

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````

## Bubble Sort
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
           1st swapping => 9 with 7, Sorted Set = {7 9 8 6 4 5 2 3}
           2nd swapping => 9 with 8, Sorted Set = {7 8 9 6 4 5 2 3}
           3rd swapping => 9 with 6, Sorted Set = {7 8 6 9 4 5 2 3}
           4th swapping => 9 with 4, Sorted Set = {7 8 6 4 9 5 2 3}     
           5th swapping => 9 with 5, Sorted Set = {7 8 6 4 5 9 2 3}     
           6th swapping => 9 with 2, Sorted Set = {7 8 6 4 5 2 9 3}       
           7th swapping => 9 with 3, Sorted Set = {7 8 6 4 5 2 3 9}   // pushed 9 at the end
After the 2nd iteration, [i=0 to i=6] 
           No swapping b/w 7 & 8,    Sorted Set = {7 8 6 4 5 2 3 9}
           1st swapping => 8 with 6, Sorted Set = {7 6 8 4 5 2 3 9}
           2nd swapping => 8 with 4, Sorted Set = {7 6 4 8 5 2 3 9}
           3rd swapping => 8 with 5, Sorted Set = {7 6 4 5 8 2 3 9}
           4th swapping => 8 with 2, Sorted Set = {7 6 4 5 2 8 3 9}   
           5th swapping => 8 with 3, Sorted Set = {7 6 4 5 2 3 8 9}   // pushed 8 at the end

and so on... till 7th iteration.

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````

## Insertion Sort
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

In the 1st iteration, only 1 element in Set
            Partial Set={9}
                    No swapping needed, Sorted Set = {9 7 8 6 4 5 2 3}
In the 2nd iteration, only 2 elements in Set
            Partial Set={9, 7}
                    1st swapping => 9 with 7, Sorted Set = {7 9 8 6 4 5 2 3}
In the 3rd iteration, only 3 elements in Set
            Partial Set={7, 9, 8}
                    1st swapping => 9 with 8, Sorted Set = {7 8 9 6 4 5 2 3} 
In the 4th iteration, only 4 elements in Set
            Partial Set={7 8 9 6} 
                    1st swapping => 9 with 6, Sorted Set = {7 8 6 9 4 5 2 3} 
                    2nd swapping => 8 with 6, Sorted Set = {7 6 8 9 4 5 2 3} 
                    3rd swapping => 7 with 6, Sorted Set = {6 7 8 9 4 5 2 3} 

and so on... till 7th iteration with all 7 elements in Set.

After all 7 iterations have finished, Sorted Set = {2 3 4 5 6 7 8 9}

````
