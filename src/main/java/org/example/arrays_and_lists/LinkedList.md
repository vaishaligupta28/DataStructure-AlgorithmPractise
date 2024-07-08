### Data Structure:  Linked List
###
#### Operations:
- Insert
- Remove
- Update
- Read
- Insert at a specific location
- Remove at a specific location
- Update at a specific location
- Read at a specific location
- Count total elements

#### Implementations:
- LinkedList in Java

#### Challenges:
- Memory allocation is non-contiguous.  
    Since modern CPUs use caching mechanisms to speed up memory access,
    and they work best when data is contiguous in memory. 
    And in linked list since data is stored scattered so there are more chances of cache misses
    leading to bad cache utilisation overall.
- Memory fragmentation is another potential issue but not a major issue.

#### Applications and real life use cases
1. Can be used to implement Stacks and Queues, De-queues and circular queues, Graphs and Trees.
2. HashTables also use linked lists for different buckets when there are collisions .
3. Bidirectional flow like Forward and Reverse webpages for browser history and music playlists.
4. Undo and Redo operations in an editor.
5. LRU cache to move efficiently from front or back based on usage.
6. Scheduling algorithm implementation like Round Robin scheduling uses Circular Linked List.
7. To keep track of turns in a multi-player game.
8. Left right swipe in the dating profile.
9. Social media content feeds.


## Code
[LinkedListImpl.class](impls/LinkedListImpl.java)

#### Time Complexity Comparison:
````
              Time Cost Summary
            ---------------------
               Best     Worst 
insert         1         N 
remove         1         N 
read           N         N 

Worst case is when traversing the list upto a specific location or end for deletion/insertion. 

````


