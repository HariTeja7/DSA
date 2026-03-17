//package com.dsa.graph;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class GraphAdjList {
//
//	static int n = 6;
//
//	static List<Integer>[] graph = new ArrayList<>[];
//	
////	List<List<Integer>> 
//
//	public static void main(String[] args) {
//
//		int[][] input = new int[][] {
//		    {0, 1}, {0, 2},        // Node 0 connects to 1 and 2
//		    {1, 3},                // Node 1 connects to 3
//		    {2, 4},                // Node 2 connects to 4
//		    {3, 5},                // Node 3 connects to 5
//		    {4, 5}                 // Node 4 connects to 5
//		};
//		
//		for (int[] i : input) {
//			graph[i[0]][i[1]] = 1;
//		}
//
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.printf(" %3d ", graph[i][j]);
//			}
//			System.out.println();
//		}
//
//		HashSet<Integer> visited1 = new HashSet<>();
//		dfsRecursion(1, visited1);
//		System.out.println();
//		System.out.println(1 + "->" + visited1);
//		System.out.println();
////		HashSet<Integer> visited2 = new HashSet<>();
////		bfsRecursion(1, visited2);
////		visited2.remove(1);
////		System.out.println(1 + "->" + visited2);
////		System.out.println();
//
//	}
//
////	public static void dfsRecursion(int src, Set<Integer> visited) {
////		for (int neighbours = 0; neighbours < n; neighbours++) {
////			boolean hasEdge = graph[src][neighbours] == 1;
////			if (hasEdge && !visited.contains(neighbours)) {
////				visited.add(neighbours);
////				System.out.println(src+"->"+neighbours);
////				dfsRecursion(neighbours, visited);
////			}
////		}
////	}
//	
//// Need to revisit
////	public static void bfsRecursion(int src, HashSet<Integer> visited) {
////		visited.add(src);
////		for (int neighbours = 0; neighbours < n; neighbours++) {
////			boolean hasEdge = graph[src][neighbours] == 1;
////			if (hasEdge && !visited.contains(neighbours)) {
////				System.out.println(src+"->"+neighbours);
////				bfsRecursion(neighbours, visited);
////			}
////		}
////	}
//
//}
