package org.example.unionfind;

/**
 * Data Structure : array
 *
 * Idea:
 *  Each disjoint set of connected elements can have same representative.
 *  E.g.
 *
 *  Sets : {1 2}, {0 5 6 7}, {4, 3, 9, 8}
 *
 *  Let's assume that the representative of Set 1 could be rightmost index. Then
 *  the representative of Set1 - is 2
 *  the representative of Set2 - is 7
 *  the representative of Set3 - is 9
 *
 *  If two elements have the same representative then those elements are connected.
 *
 *  Whe merging two elements together to connect,
 *  first one can be represented by the second one.
 *
 *  E.g. union(1, 7) => will modify the representative to be 7.
 *  And the new sets of disjoint sets will be below.
 *
 *  New Merged Set : {1 2 0 5 6 7}, {4, 3, 9, 8}
 *
 *  ******** Time Complexity ********
 *  1. Find : O(N^2) for N merges
 *  2. Union : O(1) for N merges
 *  ****** ****** ****** ****** ******
 */
public class QuickFind {

    private final int[] array;

    QuickFind(int N) {
        array = new int[N];

        for(int i =0;i<N;i++) {
            array[i] = i; // initialise the array
        }
    }

    public static void main(String[] args) {
        QuickFind quickFindObj = new QuickFind(10);
        quickFindObj.union(5, 6);
        quickFindObj.union(1, 2);

        boolean b = quickFindObj.find(1, 2);
        System.out.println("b = " + b);

        quickFindObj.printConnectedSets();
    }

    // Time Complexity : O(N)
    // for each merge transaction of any two elements
    // But for merging N elements, it can become O(N^2)
    private void union(int p, int q) {
        int parentPId = array[p];
        int parentQId = array[q];
        if (parentPId == parentQId) return; // already connected

        for(int i = 0; i < array.length; i++) {
            if(array[i] == parentPId) {
                array[i] = parentQId;
            }
        }
    }

    // Time Complexity : O(1)
    private boolean find(int p, int q) {
        return array[p] == array[q];
    }

    private void printConnectedSets() {
        boolean[] visited = new boolean[array.length];

        System.out.print("Disjoint Sets: ");

        for (int i = 0; i < array.length; i++) {
            if (!visited[i]) {
                System.out.print("{ ");
                for (int j = 0; j < array.length; j++) {
                    if (find(i, j)) {
                        visited[j] = true;
                        System.out.print(j + " ");
                    }
                }
                System.out.print("} ");
            }
        }
        System.out.println(" ");
    }
}
