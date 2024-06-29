package org.example.unionfind.exercise;

/**
 *  Problem Statement 2 : Largest element in a connected set.
 *  ----------------------------------------------------------------
 *  Union-find with specific canonical element. Add a method 𝑓𝑖𝑛𝑑() to the union-find data type
 *  so that 𝑓𝑖𝑛𝑑(𝑖) returns the largest element in the connected component containing 𝑖.
 *  The operations, 𝑢𝑛𝑖𝑜𝑛(), 𝑐𝑜𝑛𝑛𝑒𝑐𝑡𝑒𝑑(), and 𝑓𝑖𝑛𝑑() should all take logarithmic time or better.
 *
 * For example, if one of the connected components is {1,2,6,9},
 * then the 𝑓𝑖𝑛𝑑() method should return 9 for each of the four elements
 * in the connected components.
 *
 *
 * Solution Summary:
 * ----------------------------------------------------------------
 * If we always keep track of the max element in a set with each union transactions while forming a connected set and the max
 * element in a set is always updated at the root's index.
 * So when we fetch the max element in a set for any member of the set then we will only have to find the root element
 * and get the max value set at the root's index. A reason to maintain the max element at the root's index is because that
 * is what connects all its members in that set.
 *
 * Time complexity Analysis :
 * ----------------------------------------------------------------
 * Since we always track max while doing union so there is no additional time complexity added. So,
 * WightedQuickUnion takes O (logN) for merging N members
 * & we can also use WeightedQuickUnionWith PathCompression
 * approach which will be more efficient and faster with O (log* N).
 *
 *  Finding max element in a connected set takes O(log* N) - with path compression
 *
 */
public class Prob2_FindMaxInConnectedSet {
    private final int[] array;

    private final int[] size;

    private final int[] max;

    Prob2_FindMaxInConnectedSet(int N) {
        array = new int[N];
        size = new int[N];
        max = new int[N];

        for(int i=0;i<N; i++) {
            array[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    public static void main(String[] args) {
        int N = 5;
        Prob2_FindMaxInConnectedSet obj = new Prob2_FindMaxInConnectedSet(N);

        obj.union(0, 1);

        System.out.println("Largest value before creating full tree: " + obj.findMax(0));
        obj.union(2, 3);
        obj.union(1, 4);
        obj.union(1, 3);

        System.out.println("Largest value after creating full tree: " + obj.findMax(0));

        System.out.println("findMax[0]=" + obj.findMax(0));
        System.out.println("findMax[1]=" + obj.findMax(1));
        System.out.println("findMax[2]=" + obj.findMax(2));
        System.out.println("findMax[3]=" + obj.findMax(3));
        System.out.println("findMax[4]=" + obj.findMax(4));
    }

    private int root(int p) {
        if(p == array[p]) return p;

        int parent = root(array[p]); // gets the topmost parent;
        array[p] = parent; // path compression;

        return array[p];
    }

    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if(rootP == rootQ) return;

        int maxValue = Math.max(max[rootP], max[rootQ]);
        if(size[rootP] <= size[rootQ]) {
            max[rootQ] = maxValue;
            array[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            max[rootP] = maxValue;
            array[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }


    //O(C) linear time since the union operation already makes sure that the tree is flat.
    //So finding the root would be constant and hence the finding the max.
    public int findMax(int p) {
        int rootP = root(p);
        System.out.println("<<<Debug:FindMax("+p+")=>>>"+ max[rootP]);
        return max[rootP];
    }

    public void printConnectedSets() {
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

