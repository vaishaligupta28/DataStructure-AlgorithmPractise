### Data Structure:  Arrays
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
- ArrayList in Java
- Vector in Java
- SynchronizedList in Java
- CopyOnWriteArrayList in Java

#### Challenges:
1. Resizing of arrays.

#### Time Complexity Comparison:
````
                Time Cost Summary
              ---------------------
               Best     Worst   Amortized[Averaged over all operations]
initialize     1         1         1
insert         1         N         1
remove         1         N         1
read           1         1         1

Worst case is when doubling or halving operation i.e. resizing happens.

````


