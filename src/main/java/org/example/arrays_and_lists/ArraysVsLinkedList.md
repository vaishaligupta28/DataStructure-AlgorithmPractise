### Arrays v/s Linked Lists

### Which one to choose ?
So use cases where we must always have faster insertions and deletions
and cannot afford to have performance bottlenecks during insertions and deletions
then we can go for linked list otherwise we can go for arraylist.

But if you need to do faster reads and reads at a fixed index and not so many insertions and deletions then arraylist is best.

And if you are not aware of your requirements, then go with ArrayList.

### Time Complexity for diff operations b/w Arrays & LinkedList
| Operations             | ArrayList | LinkedList |
|------------------------|:---------:|:----------:|
| Insertion at beginning |   O(N)    |    O(1)    |
| Insertion at end       |   O(1)    |    O(N)    |
| Insertion at middle    |   O(N)    |    O(N)    |
| Deletion at beginning  |   O(N)    |    O(1)    |
| Deletion at end        |   O(1)    |    O(N)    |
| Deletion at middle     |   O(N)    |    O(N)    |
| Accessing              |   O(1)    |    O(N)    |


### Feature Comparisons b/w ArrayList & LinkedList
| Parameters                      |                             ArrayList                              |                                      LinkedList                                       |
|---------------------------------|:------------------------------------------------------------------:|:-------------------------------------------------------------------------------------:|
| Underlying Data Structure       |                               Array                                |                                  Doubly Linked List                                   |
| Insertion                       |             Slower <br/> <br/> Time Complexity : O(N)              |                       Faster <br/> <br/> Time Complexity: O(1)                        |
| Deletion                        |             Slower <br/> <br/> Time Complexity : O(N)              |                       Faster <br/> <br/> Time Complexity: O(1)                        |
| Traverse                        |                           Bidirectional                            |                                     Bidirectional                                     |
| Searching                       |              Faster <br/> <br/> Time Complexity: O(1)              |                       Slower <br/> <br/> Time Complexity : O(N)                       |
| Random Access                   |                                Fast                                |                                         Slow                                          |
| Memory Usage                    |      Memory efficient. It only stores the object in the list.      | Memory inefficient. It stores the object and the pointers to next and previous nodes. |
| Memory Allocation               |    Contiguous memory is allocated to all the objects i.e static    |                    Contiguous memory is not allocated i.e dynamic                     |
| Cache Friendliness              | objects are stored compactly and contiguous hence better for cache |    object storage is more scattered and not contiguous hence poor cache hits/miss     |
| Can store variable data types ? |                                 No                                 |                                          Yes                                          |



### Wiki References
https://www.youtube.com/watch?v=lC-yYCOnN8Q&list=PL2_aWCzGMAwI3W_JlcBbtYTwiQSsOTa6P&index=4&pp=iAQB