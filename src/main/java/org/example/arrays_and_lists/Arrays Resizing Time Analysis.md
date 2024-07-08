### Amortized Analysis for resizing array approaches

### Insertions
####
* #### Approach 1
Creating a new array everytime and copying all elements from prev array
to new array each time we add new elements.

````
Time Complexity Analysis
--------------------------
    Adding 1st element with initial array is of size 1, then -- newly added 1
    Adding 2nd element, a new array is created of size 2 is created -- elements copied 1 and newly added
    Adding 3rd element, a new array is created of size 3 is created -- elements copied 2 and newly added 1
    Adding 4th element, a new array is created of size 4 is created -- elements copied 3 and newly added 1

    Adding Nth element, a new array is created of size N is created -- elements copied N-1 and newly added 1

    Total time taken to resize and copying of N elements
            = (1 + 2 + 3 + 4 + .......... N) = N(N+1)/2 
            = O(N^2 /2) 
            = ~ O(N^2)
    Total time taken to insert N elements without resizing 
            = O(N)
            
    Total insertion time (with resizing)
            = O(N^2) + O (N)
            = ~ O(N^2)
````
##### Result:  Very expensive operation especially copying and creating new arrays.

####
* #### Approach 2: Repeated Doubling
Creating a new array with twice the size of the prev array
and copying elements from prev array only when the ((array is full)).


````
Time Complexity Analysis
--------------------------
    Adding 1st element with initial array is of size 1, then -- newly added 1
    Adding 2nd element, a new array is created of size 2 is created -- elements copied 1 and newly added
    Adding 3rd element, a new array is created of size 4 is created -- elements copied 2 and newly added 1
    Adding 4th element, same array is used -- newly added 1
    
    Adding 5th element, a new array is created of size 8 is created -- elements copied 4 and newly added 1
    Adding 6th element, same array is used -- newly added 1
    Adding 7th element, same array is used -- newly added 1
    Adding 8th element, same array is used -- newly added 1
    
    Adding 9th element, a new array is created of size 16 is created -- elements copied 4 and newly added 1
    
    Adding Nth element, a new array is created of size N is created -- elements copied N-1 and newly added 1
    
    Total time taken to resize and copying of N elements
        = (1 + 2 + 4 + 8 + .......... N) 
      
       If we have moved x elements and N = 2^x then,
        = (1 + 2 + 4 + 8 + .......... 2^x)
        = Sum of above geometric mean  = 2^(x+1)
        
       Since N = 2^x i.e. ===>> x = logN
            
            Therfore, sum of resizing for N elements
                = 2^(x+1) = ( 2^logN * 2^1 )
                = 2N
                = O(2N)
     
     Total time taken to insert N elements without resizing 
            = O(N)
            
     Total insertion time (with resizing)
            = O(2N) + O(N)
            = ~ O(3N)      
             
````
##### Result:  Best approach of resizing during insertion.

### Deletions
####
* #### Approach 1
Halving the array whenever it is only 1/2 full.

In this approach, there is an issue that occurs for a specific case
when there are multiple consecutive insert/delete operations consecutively.

For e.g. When there are 8 elements and the size of the array of 8 and 
as soon as 9th element is added, then the size doubles and now becomes to 16.
Now if at this point, if a delete operation is initiated then the array will be halved.
So if this same pattern keeps occurring again and again like inserting 9th element and deleting 9th element
then this will result into array doubling and halving that many times.
This is also called Thrashing.

And because of this worst case scenario,
the time complexity for N delete operations could go up-to
O (N^2). So this is not the best approach.
##### Result:  Not the most efficient approach of resizing during deletion.

####
* #### Approach 2
Halving the array whenever it is only 1/4th full.

In this approach we never encounter any issues of thrashing.
Hence, this approach is the best way to resizing the array during delete operations.

##### Result:  Most efficient approach of resizing during deletion.

## Code 
[ResizingArray.class](impls/ResizableArrayImpl.java)

### Wiki References
https://www.coursera.org/learn/algorithms-part1/supplement/UAJbP/lecture-slides - Stacks and Queues by Princeton Pdf