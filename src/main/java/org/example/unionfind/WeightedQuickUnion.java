package org.example.unionfind;


/**
 * Data Structure : array
 *
 * Idea:
 *  Although each disjoint set of connected elements can have single top root
 *  but when we are merging one node to another there are chances of merging larger to smaller node.
 *  And because of this the depth of the node keeps increasing by 1.
 *
 *  Since time taken is directly dependent on how much time is spent in finding the root which means
 *  the best solution needs to keep the depth of the node as flat as possible.
 *
 *  To avoid this, everytime we merge any node to other node, make sure to always
 *  merge the smaller node to the larger node.
 *
 *  E.g.
 *
 *  Sets : {1->2}, {0->5->6->7}, {4->3->9->8}
 *
 *  Whe merging two elements together to connect,
 *  check the root of the first node and second node and keep track of the count of the total elements in both the merging group
 *  & merge the group which has the smallest weight under the largest group.
 *  Such that the largest group's root is the final root of the merged group.
 *
 *
 *  E.g.
 *        union(7, 1) with normal QuickUnion => will make the final top root to be 1.
 *
 *        union(7, 1) with weighted QuickUnion => will change the top root instead to be 7.
 *
 *  And the new sets of disjoint sets will be below.
 *  New Merged Set : {1->2->7<-6<-5<-0}, {4->3->9->8}
 *
 *
 *  ******** Time Complexity [N elements,M operations] ********
 *  Let's talk about the time complexity,
 *
 *  After adding weighted logic, in the worst case scenario, the depth of the largest node
 *  will be always less than or equal to `logN`, given N total nodes in a tree.
 *  Check the class #TimeAnalysisWeightedVsNonWeighted for more information.
 *
 *  Reference
 *  *  <a href="https://www.youtube.com/watch?v=QgfiUD9de1Y&list=PLKSaHgNv33YitEFQDDKci04EhznR_sRn3&index=63&pp=iAQB">...</a>
 *
 *  N    Max Depth
 *  1    0
 *  2    1
 *  4    2
 *  8    3
 *  16   4
 *
 *  1. Find : O(M logN)
 *  2. Union : O(M logN)
 *
 *  Now height of the tree will never grow more than logN.
 *  ****** ****** ****** ****** ****** ****** ****** ****** ******
 */

public class WeightedQuickUnion {

    private final int[] array;
    private final int[] size;


    private final int[] depth; // for auditing only
    int maxDepthNode = -1;

    int finalDepth;

    WeightedQuickUnion(int N) {
        array = new int[N];
        size = new int[N];
        depth = new int[N]; // for auditing only

        for(int i=0; i<N; i++) {
            array[i] = i; // initialise the array
            size[i] = 1; // initialise the array

            depth[i] = 0; // initialize depth // for auditing only
        }
    }

    public static void main(String [] args) {
        WeightedQuickUnion weightedQuickUnionObj = new WeightedQuickUnion(10);
        weightedQuickUnionObj.union(5, 6);
        weightedQuickUnionObj.union(6, 3);
        weightedQuickUnionObj.union(1, 2);

        weightedQuickUnionObj.printConnectedSets();
        boolean b = weightedQuickUnionObj.find(3, 1);
        System.out.println("\nAre 1 and 3 connected? = " + b);

        weightedQuickUnionObj.union(3,1);
        boolean c = weightedQuickUnionObj.find(3, 1);
        System.out.println("\nAre 1 and 3 connected? = " + c);

        weightedQuickUnionObj.printConnectedSets();
    }

    private int root(int i) {
        while(i!=array[i]) {
            i = array[i];
        }
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        System.out.println("--->>Union ["+p+","+ q+"]");
        int rootPId = root(p);
        int rootQId = root(q);

        if(rootPId == rootQId) return;

        if(size[rootPId] <= size[rootQId]) {
            System.out.println("After merging root " + rootPId + " to root " + rootQId +" \nRoot is now: "+ rootQId);
            array[rootPId] = rootQId;
            size[rootQId]+=size[rootPId];
            System.out.println("Size of the tree=> "+ size[rootQId]);
        } else {
            System.out.println("After merging root " + rootQId + " to root " + rootPId +" \nRoot is now: "+ rootPId);
            array[rootQId] = rootPId;
            size[rootPId]+=size[rootQId];
            System.out.println("Size of the tree=> "+ size[rootPId]);
        }
        System.out.println("--------------------------------");

        // for auditing only ---- Optional part added for time comparison
        // #TimeAnalysisWeightedVsNonWeighted
        // Update depth
        int newDepth = depth[rootPId] == depth[rootQId]
                ? Math.max(depth[rootPId], depth[rootQId]) + 1 : Math.max(depth[rootPId], depth[rootQId]);
        depth[rootPId] = depth[rootQId] = newDepth;


        // Update max depth node if needed
        if (maxDepthNode == -1 || newDepth > depth[maxDepthNode]) {
            maxDepthNode = rootPId; // or rootQId, both are same after union
        }
    }

    private void printConnectedSets() {
        boolean[] visited = new boolean[array.length];

        System.out.print("Final Disjoint Sets: ");

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
        return depth[maxDepthNode];
    }
}
