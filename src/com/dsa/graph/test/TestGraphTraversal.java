package com.dsa.graph.test;

import com.dsa.graph.Graph;
import com.dsa.graph.GraphAdjList;
import com.dsa.graph.GraphAdjMatrix;

public class TestGraphTraversal {

	public static void main(String[] args) {
		// Input
		int n = 11;
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 5, 7 }, { 6, 7 }, { 7, 8 },
				{ 8, 9 } };
		cycleTest(n, edges, new GraphAdjList(n));
		cycleTest(n, edges, new GraphAdjList(n, true));
		cycleTest(n, edges, new GraphAdjMatrix(n));
		cycleTest(n, edges, new GraphAdjMatrix(n, true));
	}

	private static void cycleTest(int n, int[][] edges, Graph graph) {
		for (int[] edge : edges) {
			graph.addEdge(edge[0], edge[1]);
		}

		// Graph Print
		graph.print();

		/*
		 * Connected Components 1) isConnected() → false Reason: 3 components exist. 2)
		 * countComponents() → 3
		 */
		System.out.println("Graph is connected : " + graph.isConnected());
		System.out.println("Graph components : %s".formatted(graph.components().toString()));

		/*
		 * Traversal 3) DFS Traversal 4) BFS Traversal
		 */
		for (int u = 0; u < graph.getNoOfVertices(); u++) {
			System.out.println("DFS Recursion for " + u + " : " + graph.dfsWithRecursion(u));
			System.out.println("DFS for " + u + " : " + graph.dfs(u));
			System.out.println("BFS for " + u + " : " + graph.bfs(u));
			System.out.println("----------------");
		}

		/*
		 * 5) pathExists(0,4) → true pathExists(0,9) → false pathExists(5,9) → true
		 * pathExists(10,1) → false
		 */
		System.out.println("Graph path exists between 0 and 4 -> " + graph.pathExists(0, 4));
		System.out.println("Graph path exists between 0 and 9 -> " + graph.pathExists(0, 9));
		System.out.println("Graph path exists between 5 and 9 -> " + graph.pathExists(5, 9));
		System.out.println("Graph path exists between 10 and 1 -> " + graph.pathExists(10, 1));

		/*
		 * 6) path(0,4) → [0,1,3,4] // or [0,2,3,4] path(5,9) → [5,7,8,9] // or
		 * [5,6,7,8,9] path(0,9) → [] // no path
		 */
		System.out.println("Graph path between 0 and 4 -> " + graph.path(0, 4));
		System.out.println("Graph path between 5 and 9 -> " + graph.path(5, 9));
		System.out.println("Graph path between 0 and 9 -> " + graph.path(0, 9));
	}

}
