package org.example.unionfind;


/**
 * Data Structure : array
 *
 * Idea:
 *  Each disjoint set of connected elements can have single top root.
 *  E.g.
 *
 *  Sets : {1->2}, {0->5->6->7}, {4->3->9->8}
 *
 *  If two elements have the same top root then those elements are connected.
 *
 *  Whe merging two elements together to connect,
 *  check the root of the first node and second node and make one of them as root for another one.
 *
 *  E.g. union(1, 7) => will modify the top root to be 7.
 *  And the new sets of disjoint sets will be below.
 *
 *  New Merged Set : {1->2->7<-6<-5<-0}, {4->3->9->8}
 *
 *  ******** Time Complexity ********
 *  1. Find : O(N^2) for N merges
 *  2. Union : O(N^2) for N finds
 *  ****** ****** ****** ****** ******
 */
public class QuickUnion {

    private final int[] array;

    private final int[] size;

    private final int[] depth; // for auditing only
    int maxDepthNode = -1;

    QuickUnion(int N) {
        array = new int[N];
        size = new int[N];
        depth = new int[N]; // for auditing only


        for(int i=0; i<N; i++) {
            array[i] = i; // initialise
            size[i] = 1; // initialise the array
            depth[i] = 0; // initialize depth // for auditing only
        }
    }

    public static void main(String[] args) {
        QuickUnion quickUnionObj = new QuickUnion(10);
        quickUnionObj.union(5, 6);
        quickUnionObj.union(1, 2);

        boolean b = quickUnionObj.find(1, 2);
        System.out.println("b = " + b);

        quickUnionObj.printConnectedSets();
    }

    // Finding root node.
    // Worst case if the depth of the tree become N.
    // Time Complexity : O(N)
    private int root(int i) {
        while(i != array[i]) {
            i = array[i];
        }
        return i;
    }

    // Merge two nodes.
    // Time Complexity : O(N) as it includes finding the root.
    // Worst case is when the depth of the node becomes N.
    // Challenge : Tree can become too tall.
    public void union(int p, int q) {
        System.out.println("--->>Union ["+p+","+ q+"]");

        int rootPId = root(p);
        int rootQId = root(q);

        if(rootPId == rootQId) return; // already connected

        array[rootPId] = rootQId; // set one root to another
        size[rootQId]+=size[rootPId];
        System.out.println("After merging root " + rootPId + " to root " + rootQId +" \nRoot is now: "+ rootQId);
        System.out.println("Size of the tree=> "+ size[rootQId]);


        // for auditing only ---- Optional part added for time comparison
        // #TimeAnalysisWeightedVsNonWeighted
        // Update depth
        int newDepth = Math.max(depth[rootPId], depth[rootQId]) + 1;
        depth[rootPId] = depth[rootQId] = newDepth;

        if(maxDepthNode == -1) {
            maxDepthNode = rootPId; // or rootQId, both are same after union
        }

        // Update max depth node if needed
        if (newDepth > depth[maxDepthNode]) {
            maxDepthNode = rootPId; // or rootQId, both are same after union
        }
    }

    // Find whether two nodes are connected.
    // Time Complexity : O(N) as it includes finding the root.
    // Worst case is when the depth of the node becomes N.
    // Challenge : Tree can become too tall.
    public boolean find(int p, int q) {
        return root(p) == root(q); // if roots of two elements is same.
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

    public int findMaxDepthCount() {
        return array[maxDepthNode];
    }
}
