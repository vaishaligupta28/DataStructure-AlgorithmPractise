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
 * ----------------------------
 *  Additional Points to Note:
 *  ----------------------------
 *
 *  1. The `finding root` function can be optimized by pointing the node to the topmost root instead of
 *  just pointing to the grandparent. Can be achieved using recursion.
 *  Check `rootByRecur` instead of `root` for the same.
 *
 *  2. The merging logic i.e. `union` operation can be done in
 *  two ways either using height of the tree or size of the tree. Also called UnionByRank and UnionBySize respectively.
 *  In weighted quick union, we can either use height or size of the tree because we are not dynamically changing the depth.
 *  But with path compression the height of the tree cannot be determined any longer since changing the path might or might not change the overall depth.
 *  Hence, UnionByRank is not possible in this solution, and we have to use only Union By Size.
 *
 *  3. Since path compress already takes care of compressing the depth of the tree in the most efficient way so
 *   using weighted approach by Union By Size does not optimize any further hence we can also do without that as well.
 * ----------------------------
 *
 *  References :
 *  <a href="https://youtu.be/e3NkMl3evDg?list=PL8FaHk7qbOD44CGmkFro1gqxkDl5gIpXB">...</a>
 *  <a href="https://youtu.be/aBxjDBC4M1U">...</a>
 */
public class WeightedQuickUnionWithPathCompress {

    private final int[] array;
    private final int[] size;

    WeightedQuickUnionWithPathCompress(int N) {
        array = new int[N];
        size = new int[N];

        for(int i = 0; i < N; i++) {
            array[i] = i;
            size[i] = 1;
        }
    }

    WeightedQuickUnionWithPathCompress(int[] inputArr, int[] sizeArr) {
        array = inputArr;
        size = sizeArr;

        for(int i = 0; i < inputArr.length; i++) {
            array[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnionWithPathCompress obj = new WeightedQuickUnionWithPathCompress(10);
        obj.union(0, 1);
        obj.union(2, 3);
        obj.union(4, 5);
        obj.union(2, 4);
        System.out.println("obj.find(0, 2) = " + obj.find(0, 2));

        obj.union(0, 2);

        System.out.println("obj.find(0, 2) = " + obj.find(0, 2));

        obj.printConnectedSets();
    }

    // Connects the grandparent instead of parent to a node.
    private int root(int i) {
        // Path compression
        while (i != array[i]) {
            if(array[i] != array[array[i]]) {
                System.out.println("Compress the path from [" + i + "] to its root.");
                array[i] = array[array[i]]; // Path compression by halving [changing from parent root to grandparent root]
                i = array[i];
            }
        }
        return i;
    }


    // [Better approach] Finds the ultimate root or parent and points indirect nodes directly to the parent itself.
    private int rootByRecur(int i) {
        if(i == array[i]) return i;
        int root = rootByRecur(array[i]);
        array[i]  = root;
        return array[i];
    }

    public boolean find(int p, int q) {
        return rootByRecur(p) == rootByRecur(q);
    }

    // This function uses Union By Size for merging two nodes.
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
}
