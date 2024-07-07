Tradeoffs between Linked List and Arrays

- Linked List
1. Each operation in worst case always takes a constant time for insertion and deletion.
2. Takes additional memory space for storing references and links etc...

- Resizing Array or ArrayList
1. Averaged over multiple operations i.e. amortised time for insertion and deletion is constant.
But there will be certain operations when array is being resized that the time could take proportional to N.

2. Takes less memory space than linked list.


So use cases where we must always have constant time
then we can go for linked list otherwise we can go for arraylist.