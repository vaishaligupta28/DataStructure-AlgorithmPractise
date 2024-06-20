package org.example.unionfind;

public class TimeAnalysisWeightedVsNonWeighted {

    public static void main(String[] args){

        QuickUnion quickUnion = new QuickUnion(10);
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);

        System.out.println("---------Merging using QuickUnionUnion---------");
        quickUnion.union(0, 1);
        quickUnion.union(1, 2);
        quickUnion.union(2, 3);
        quickUnion.union(3, 4);
        quickUnion.union(4, 5);
        int maxDepthCountAfterQuickUnion = quickUnion.findMaxDepthCount();
        System.out.println("-------[After Quick Union]Largest depth node count = " + maxDepthCountAfterQuickUnion);

        System.out.println("---------Merging using Weighted QuickUnionUnion--------");
        weightedQuickUnion.union(0, 1);
        weightedQuickUnion.union(1, 2);
        weightedQuickUnion.union(2, 3);
        weightedQuickUnion.union(3, 4);
        weightedQuickUnion.union(4, 5);
        int maxDepthCountAfterWeightedQuickUnion = weightedQuickUnion.findMaxDepthCount();
        System.out.println("-----[After Weighted Quick Union]Largest depth node count = " + maxDepthCountAfterWeightedQuickUnion);

        if(maxDepthCountAfterQuickUnion > maxDepthCountAfterWeightedQuickUnion) {
            System.out.println("\n\n Hence Proved!  Weighted QuickUnion is faster than QuickUnion");
        }

    }
}
