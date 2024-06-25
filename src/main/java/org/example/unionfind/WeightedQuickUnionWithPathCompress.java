package org.example.unionfind;

/**
 *
 * Data Structure : Array
 *
 * Idea : Best solution
 * When merging nodes into another, finding each node's root requires traversing through the path to its root.
 * To optimize this, we compress the path during traversal itself.
 *
 * How to implement:
 * To achieve this, we just to need to change the parent of the node and point instead to the grandparent node.
 * If `i` represent a node, then array[i] represents the parent and the array[array[i]] represents the grandparent.
 *
 * Impact on time complexity:
 * The time complexity of union-find operations depends on finding the root,
 * which relates to the tree depth.
 * By ensuring the depth doesn't increase and even decreases after each union, we achieve optimal time complexity.
 *
 * This approach ensures that each tree's depth decreases, leading to a flat and compact tree structure.
 *
 * Mathematically time complexity of this approach is represented by
 *
 *     O (log* N) - where this (log*) is an iteratively increasing logarithmic function.
 *              [Basically it represents num of times would you have to do log2 before it reaches 1]
 *              [log* N ~=  log(log(log....(log N))) until it reaches 1]
 *
 *              Use  <a href="https://joshh.ug/logstar/demo.html">...</a> to do log2 calculation for a very big N
 *              till you find the biggest numeral's log* N is not more than 5.
 *              E.g. log*(123456789999999) was only : 5
 *                  & log*(1234567899999999999999) was also: 5
 *
 *              This is simple example of a very, very slow growing function.
 *              We can also consider such type of functions as linearly or constant time fucntions
 *              So simply it is very much a ````constant or linear time```` complexity solution.
 *
 *      or O(α(N)) where (α) represents amortised time function, also called inverse Ackermann function
 *          This also represents a slow growing function even slower than log* N.
 *          (2^2^....2^i) where (i) represents num of times when exponented by 2 to reach a very big N.
 *          Mostly we find if we keep increasing `i` till 5 we get a fairly big N.
 *          2^2 = 4
 *          2^2^2 = 4*4 = 16
 *          2^2^2^2 = 16*16 = 65536
 *          2^2^2^2^2 = 65536*65536 =  ... A very big number.
 *
 *  ******* Time Complexity [N elements,M operations] ********
 *  1. Find : O(M log* N) or O(M α(N)) where α represents amortised time function
 *  2. Union : O(M log* N) or O(M α(N)) where α represents amortised time function
 *  ****** ****** ****** ****** ****** ****** ****** ****** ******
 *
 *  Reference :
 *  <a href="https://youtu.be/e3NkMl3evDg?list=PL8FaHk7qbOD44CGmkFro1gqxkDl5gIpXB">...</a>
 */
public class WeightedQuickUnionWithPathCompress {

    private final int[] array;
    private final int[] size;


    private final int[] depth; // for auditing only
    int maxDepthNode = -1;

    WeightedQuickUnionWithPathCompress(int N) {
        array = new int[N];
        size = new int[N];
        depth = new int[N]; // for auditing only

        for(int i = 0; i < N; i++) {
            array[i] = i;
            size[i] = 1;

            depth[i] = 0; // initialize depth // for auditing only
        }
    }

    WeightedQuickUnionWithPathCompress(int[] inputArr, int[] sizeArr, int[] depthArr) {
        array = inputArr;
        size = sizeArr;
        depth = depthArr; // for auditing only

        for(int i = 0; i < inputArr.length; i++) {
            array[i] = i;
            size[i] = 1;

            depth[i] = 0; // initialize depth // for auditing only
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionWithPathCompress obj = new WeightedQuickUnionWithPathCompress(10);
        obj.union(5, 6);
        obj.union(1, 2);

        boolean b = obj.find(1, 2);
        System.out.println("b = " + b);

        obj.printConnectedSets();
    }

    private int root(int i) {
        // Path compression
        while (i != array[i]) {
            if(array[i] != array[array[i]]) {
                System.out.println("Compress the path from [" + i + "] to its root.");
                array[i] = array[array[i]]; // Path compression by halving [changing from parent root to grandparent root]
                depth[i]--;
            }
            i = array[i];
        }
        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
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
