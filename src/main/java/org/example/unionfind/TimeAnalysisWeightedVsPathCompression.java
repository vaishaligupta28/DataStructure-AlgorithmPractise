package org.example.unionfind;

/**
 * This class compares two different approaches of quick union
 * one without weighted and another with weighted.
 */
public class TimeAnalysisWeightedVsPathCompression {

    public static void main(String[] args){

        QuickUnion quickUnion = new QuickUnion(10);
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10);
        WeightedQuickUnionWithPathCompress weightedQuickUnionWithPathCompress =
                new WeightedQuickUnionWithPathCompress(10);


        System.out.println("---------Merging using QuickUnionUnion---------");
        quickUnion.union(0, 1);
        quickUnion.union(2, 3);
        quickUnion.union(4, 5);
        quickUnion.union(2, 4);
        quickUnion.union(0, 4);
        int maxDepthCountAfterQuickUnion = quickUnion.findMaxDepthCount();
        System.out.println("-------[After Quick Union]Largest depth node count = " + maxDepthCountAfterQuickUnion);

        System.out.println("---------Merging using Weighted QuickUnionUnion--------");
        weightedQuickUnion.union(0, 1);
        weightedQuickUnion.union(2, 3);
        weightedQuickUnion.union(4, 5);
        weightedQuickUnion.union(2, 4);
        weightedQuickUnion.union(0, 4);
        int maxDepthCountAfterWeightedQuickUnion = weightedQuickUnion.findMaxDepthCount();
        System.out.println("-----[After Weighted Quick Union]Largest depth node count = " + maxDepthCountAfterWeightedQuickUnion);


        System.out.println("---------Merging using Weighted QuickUnionUnion With Path Compress--------");
        weightedQuickUnionWithPathCompress.union(0, 1);
        weightedQuickUnionWithPathCompress.union(2, 3);
        weightedQuickUnionWithPathCompress.union(4, 5);
        weightedQuickUnionWithPathCompress.union(2, 4);
        weightedQuickUnionWithPathCompress.union(0, 2);
    }
}
