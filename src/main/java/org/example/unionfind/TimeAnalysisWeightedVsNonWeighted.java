package org.example.unionfind;

/**
 * This class compares two different approaches of quick union
 * one without weighted and another with weighted.
 */
public class TimeAnalysisWeightedVsNonWeighted {

    public static void main(String[] args){

        QuickUnion quickUnion = new QuickUnion(10);
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);

        System.out.println("---------Merging using QuickUnionUnion---------");
        //Set 1: {0, 1, 2}
        quickUnion.union(0, 1);
        quickUnion.union(1, 2);

        //Set 2: {3, 4, 5, 6}
        quickUnion.union(3, 4);
        quickUnion.union(4, 5);
        quickUnion.union(5, 6);
        int maxDepthCountAfterQuickUnion = quickUnion.findMaxDepthCount();
        System.out.println("-------[After Quick Union]Largest depth node count = " + maxDepthCountAfterQuickUnion);

        System.out.println("\n---------Merging using Weighted QuickUnionUnion--------");
        //Set 1: {0, 1, 2}
        weightedQuickUnion.union(0, 1);
        weightedQuickUnion.union(1, 2);

        //Set 2: {3, 4, 5, 6}
        weightedQuickUnion.union(3, 4);
        weightedQuickUnion.union(4, 5);
        weightedQuickUnion.union(5, 6);
        int maxDepthCountAfterWeightedQuickUnion = weightedQuickUnion.findMaxDepthCount();
        System.out.println("-----[After Weighted Quick Union]Largest depth node count = " + maxDepthCountAfterWeightedQuickUnion);

        if(maxDepthCountAfterQuickUnion > maxDepthCountAfterWeightedQuickUnion) {
            System.out.println("\n\n Hence Proved!  Weighted QuickUnion is faster than QuickUnion");
        }
    }
}
