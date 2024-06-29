package org.example.unionfind.exercise;

/**
 * Problem Statement 3 : Successor with delete.
 *
 * Successor with delete. Given a set of 𝑛 integers 𝑆={0,1,...,𝑛−1} and a sequence of requests of the following form:
 * - Remove 𝑥 from 𝑆
 * - Find the successor of 𝑥: the smallest 𝑦 in 𝑆 such that 𝑦≥𝑥.
 *   Design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 *
 * Solution Summary:
 * ----------------------------------------------------------------
 *  There are two requirements
 *  1. we need to remove an element and then
 *  2. we need to figure out its immediate successor
 *
 *  In order to achieve this in < logN time, we can use Quick Union data structure.
 *  i.e. If we insert(i.e. union) the deleted element with its immediate(deleted or undeleted) successor to Quick Union data structure
 *  each time when the deletion occurs, then it will be a matter of just finding the largest element(findMax)
 *  in the connected set where the deleted element belongs.
 *
 * Example: When 2 is deleted then 2 is merged into 3.
 * <a href="https://i.sstatic.net/mj4zX.png">...</a>
 *
 * References:
 * <a href="https://stackoverflow.com/a/42863015">...</a> - Solution Reference
 *
 *
 * Time complexity Analysis :
 * ----------------------------------------------------------------
 *
 * For every deletion(remove) it will take O(log* N) to merge the deleted element into the
 * successor.
 * And then (successor) operation it will take O(log* N) to find the largest element(findMax)
 *
 * Overall Time Complexity
 * - Initialization: O(N)
 * - Per Operation (remove or successor): O(𝛼(N)) or O(log* N)
 */
public class Prob3_SuccessorWithDelete {
    private final boolean[] active; // data[i] == false if removed
    private final Prob2_FindMaxInConnectedSet uf; // used to find largest un-removed element
    private final int N; // N integers in S

    public Prob3_SuccessorWithDelete(int N) {
        this.N = N;
        active = new boolean[N];
        for (int i = 0; i < N; ++i)
            active[i] = true;
        uf = new Prob2_FindMaxInConnectedSet(N);
    }

    /**
     * Remove an element x from Set S.
     * @param x the element to search for successor
     */
    public void remove(int x) {
        if(x >= 0 && !active[x]) return; //if already removed

        active[x] = false; //mark as removed
        if (x < N-1) {
            uf.union(x, x+1); // Merge the deleted element with its undeleted successor
        }
    }

    /**
     * Find the successor for an element x from Set S.
     * @param x the element to search for successor
     * @return the successor
     *
     * Steps -
     * - Finds the successor of 𝑥.
     * - If 𝑥 is active, returns 𝑥 itself.
     * - If 𝑥 is not active and its direct successor 𝑥+1 is active, returns 𝑥+1.
     * - If both 𝑥 & 𝑥+1 are removed, finds the maximum element in the connected set using the Quick Union structure.
     */
    public int successor(int x) {
        if(active[x]) return x; // x is already active then return the same

        // if successor is not yet removed then returns as is.
        if(x != N-1 && active[x+1]) return x + 1;
        else {
           int max = x == N-1 ? uf.findMax(x) : uf.findMax(x+1); // find max
           if(!active[max]) return -1; // no active successors found when even the [N-1]th last element is also deleted.

           return max;
       }
    }

    public static void main(String[] args) {
        Prob3_SuccessorWithDelete swd = new Prob3_SuccessorWithDelete(10);

        // Case 1: When x and x+1 both are not removed
        System.out.println("----------No elements are removed!----------");
        System.out.println("swd.successor(0)=" + swd.successor(0));
        System.out.println("swd.successor(2)=" + swd.successor(2));
        System.out.println("swd.successor(6)=" + swd.successor(6));

        // Case 2: When x is removed and x+1 is not removed then get directly
        System.out.println("\n----------remove(2)----------");
        swd.remove(2);
        swd.printDeletedSets();
        System.out.println("swd.successor(2)=" + swd.successor(2));

        System.out.println("\n----------remove(6)----------");
        swd.remove(6);
        swd.printDeletedSets();
        System.out.println("swd.successor(6)=" + swd.successor(6));

        // Case 3: When x+1 is also removed, then check the deleted tree connected set for max element.
        System.out.println("\n----------remove(7)----------");
        swd.remove(7);
        swd.printDeletedSets();
        System.out.println("swd.successor(6)=" + swd.successor(6));
        System.out.println("swd.successor(7)=" + swd.successor(7));

        // Case 5: When x, x+1, x+2, 3 consecutive's are removed, then also check the deleted tree connected set for max element.
        System.out.println("\n----------remove(8)----------");
        swd.remove(8);
        swd.printDeletedSets();
        System.out.println("swd.successor(6)=" + swd.successor(6));
        System.out.println("swd.successor(7)=" + swd.successor(7));
        System.out.println("swd.successor(8)=" + swd.successor(8));
        System.out.println("swd.successor(9)=" + swd.successor(9));

        // Case 6: When x=(N-1)th element is removed, then we just mark it inactive but not merge anything in the deleted tree set
        // And when we do a successor call, that's when we handle it by checking if the successor found[findMax(9)] is active or not.
        // If not active then that means we have no valid successor.
        System.out.println("\n----------remove(9)----------");
        swd.remove(9);
        swd.printDeletedSets();
        System.out.println("swd.successor(6)=" + swd.successor(6));
        System.out.println("swd.successor(7)=" + swd.successor(7));
        System.out.println("swd.successor(8)=" + swd.successor(8));
        System.out.println("swd.successor(9)=" + swd.successor(9));

        // Case 6: When x=0th element is removed, then nothing changes.
        System.out.println("\n----------remove(0)----------");
        swd.remove(0);
        System.out.println("swd.successor(0)=" + swd.successor(0));
        System.out.println("swd.successor(1)=" + swd.successor(1));
        System.out.println("swd.successor(2)=" + swd.successor(2));

        // Print deleted sets
        swd.printDeletedSets();
    }

    private void printDeletedSets() {
        System.out.println("----------Print Deleted Elements Tree----------");
        uf.printConnectedSets();
    }
}
