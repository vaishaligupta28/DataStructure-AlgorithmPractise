package org.example.unionfind.exercise;

import java.util.*;

/**
 *
 * Problem Statement 1 : Social network connectivity
 * ----------------------------------------------------------------
 *
 * Given a social network containing 𝑛 members and a log file containing 𝑚 timestamps at which times pairs of members
 * formed friendships, design an algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
 * The running time of your algorithm should be 𝑚log𝑛 or better and use extra space proportional to 𝑛.
 *
 * Solution Summary :
 * ----------------------------------------------------------------
 * For all members of the friendship network to be connected together they need to be part of
 * a single tree such that the topmost parent of each member is same.
 *
 * What is given to us is below ---
 * - We know which pairs get connected at what timestamps and how many transactions have occurred i.e M.
 * - We know the log data is also sorted as per timestamp so transactions are logged from earliest to latest.
 * - We also know that the friendship network also form an equivalence relation which means if A is connected to B and B is connected to C, then A is also connected to C.
 *
 * With the above given requirements the closest data structure that can be used for this scenario is Union Find DSA.
 * We can start from the 0th transaction unto M transactions that can be added in the network by performing
 * a `union` operation using Weighted QuickUnion approach.
 * Now as we said to know if all members are connected, they all need to have same root which means
 * the size of the root index has to be equal to N for it to have N members connected to it.
 *
 * Since in WeightedQuickUnion, we keep updating the size of the merged root, we already keep track of the resulting
 * root's size to decide which is the smaller entity to be merged under the larger one.
 * So whenever we reach a point when the size of the resulting root after doing the union becomes equal to N,
 * then we know that this is the transaction when all members got connected and the timestamp respective is the earliest time when it happened.
 *
 * Time complexity Analysis :
 * ----------------------------------------------------------------
 * Since weighted quick union's worst time complexity is O(logN) and since we might have to in do in worst case M transactions
 * so for the complete algorithm it would take O (M logN).
 * And we use extra space proportional to N for maintaining the size of each the members.
 */
public class Prob1_SocialNetworkConnectivityTimestamps {

    private final int[] array;
    private final int[] size;
    private int count;

    Prob1_SocialNetworkConnectivityTimestamps(int N) {
        array = new int[N];
        size = new int[N];

        for (int i=0;i<N;i++) {
            array[i] = i;
            size[i] = 1;
        }
    }

    public static void main(String[] args) {
        int N = 5;
        Prob1_SocialNetworkConnectivityTimestamps obj = new Prob1_SocialNetworkConnectivityTimestamps(N);

        List<LogData> list = new ArrayList<>();

        list.add(new LogData(0, 2, "t0"));
        list.add(new LogData(1, 3, "t1"));
        list.add(new LogData(1, 4, "t2"));
        list.add(new LogData(0, 1, "t3"));

        String earliestTimestamp = "";
        for (LogData logData : list) {
            if(!obj.isConnected(logData.element1(), logData.element2())) {
                obj.union(logData.element1(), logData.element2());
                if (obj.getCount() == N) {
                    earliestTimestamp = logData.timestamp();
                    break;
                }
            }
        }

        if(earliestTimestamp == null || earliestTimestamp.isBlank()) {
            System.out.println("Warning! All members could not be connected! LogFile looks incomplete!");
        } else {
            System.out.println("The earliest timestamp when all members are connected is = " + earliestTimestamp);
        }
    }

    private int getCount()  {
        return this.count;
    }

    private record LogData(int element1, int element2, String timestamp) {
    }


    private int root(int p) {
        while(p != array[p]) {
            p = array[p];
        }
        return p;
    }


    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);

        if(rootP == rootQ) return;

        if(size[rootP] <= size[rootQ]) {

            array[rootP] = rootQ;
            size[rootQ] +=size[rootP];
            count = size[rootQ];
        } else {

            array[rootQ] = rootP;
            size[rootP] += size[rootQ];
            count = size[rootP];
        }
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }
}


