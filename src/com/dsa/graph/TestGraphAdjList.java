package com.dsa.graph;

public class TestGraphAdjList {

	public static void main(String[] args) {
		// Input
		int n = 11;
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 5, 7 }, { 6, 7 }, { 7, 8 },
				{ 8, 9 } };

		Graph graph = new GraphAdjList(n);
		for (int[] edge : edges) {
			graph.addEdge(edge[0], edge[1], true);
		}

		// Graph Print
		graph.print();

		/*
		 * Connected Components 1) isConnected() → false Reason: 3 components exist. 2)
		 * countComponents() → 3 3)
		 */
		System.out.println("Graph is connected : " + graph.isConnected());
		System.out.println("Graph components : %s".formatted(graph.components().toString()));

		/*
		 * Traversal 3) DFS Traversal 4) BFS Traversal
		 */
		for (int u = 0; u < graph.getNoOfVertices(); u++) {
			System.out.println(graph.dfsWithRecursion(u));
			System.out.println(graph.dfs(u));
			System.out.println(graph.bfs(u));
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
