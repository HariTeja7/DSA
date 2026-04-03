package com.dsa.graph.test;

import com.dsa.graph.Graph;
import com.dsa.graph.GraphAdjList;
import com.dsa.graph.GraphAdjMatrix;
import com.dsa.graph.disjointset.UnionBySize;

public class TestGraphCycle {

	public static void main(String[] args) {
		// -------- 1) No Cycle (Tree) --------
		int n1 = 6;
		int[][] edges1 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		// Expected: false

		// -------- 2) Single Cycle --------
		int n2 = 5;
		int[][] edges2 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 }, { 3, 4 } };
		// Expected: true

		// -------- 3) Multiple Components (One cycle) --------
		int n3 = 8;
		int[][] edges3 = { { 0, 1 }, { 1, 2 }, { 2, 0 }, // cycle
				{ 3, 4 }, { 4, 5 }, // no cycle
				{ 6, 7 } // no cycle
		};
		// Expected: true

		// -------- 4) Multiple Components (No cycle) --------
		int n4 = 7;
		int[][] edges4 = { { 0, 1 }, { 1, 2 }, { 3, 4 }, { 5, 6 } };
		// Expected: false

		// -------- 5) Self Loop --------
		int n5 = 3;
		int[][] edges5 = { { 0, 1 }, { 1, 2 }, { 2, 2 } };
		// Expected: true

		// -------- 6) Parallel Edge --------
		int n6 = 2;
		int[][] edges6 = { { 0, 1 }, { 0, 1 } };
		// Expected: true

		// -------- 7) Your Graph --------
		int n7 = 11;
		int[][] edges7 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 5, 7 }, { 6, 7 }, { 7, 8 },
				{ 8, 9 } };
		// Expected: true

		traversalTest(edges1, new GraphAdjMatrix(n1));
		traversalTest(edges2, new GraphAdjMatrix(n2));
		traversalTest(edges3, new GraphAdjMatrix(n3));
		traversalTest(edges4, new GraphAdjMatrix(n4));
		traversalTest(edges5, new GraphAdjMatrix(n5));
		traversalTest(edges6, new GraphAdjMatrix(n6));
		traversalTest(edges7, new GraphAdjMatrix(n7));

		traversalTest(edges1, new GraphAdjList(n1));
		traversalTest(edges2, new GraphAdjList(n2));
		traversalTest(edges3, new GraphAdjList(n3));
		traversalTest(edges4, new GraphAdjList(n4));
		traversalTest(edges5, new GraphAdjList(n5));
		traversalTest(edges6, new GraphAdjList(n6));
		traversalTest(edges7, new GraphAdjList(n7));

		traversalTest(edges1, new GraphAdjMatrix(n1, false, new UnionBySize(n1)));
		traversalTest(edges2, new GraphAdjMatrix(n2, false, new UnionBySize(n2)));
		traversalTest(edges3, new GraphAdjMatrix(n3, false, new UnionBySize(n3)));
		traversalTest(edges4, new GraphAdjMatrix(n4, false, new UnionBySize(n4)));
		traversalTest(edges5, new GraphAdjMatrix(n5, false, new UnionBySize(n5)));
		traversalTest(edges6, new GraphAdjMatrix(n6, false, new UnionBySize(n6)));
		traversalTest(edges7, new GraphAdjMatrix(n7, false, new UnionBySize(n7)));

		traversalTest(edges1, new GraphAdjList(n1, false, new UnionBySize(n1)));
		traversalTest(edges2, new GraphAdjList(n2, false, new UnionBySize(n2)));
		traversalTest(edges3, new GraphAdjList(n3, false, new UnionBySize(n3)));
		traversalTest(edges4, new GraphAdjList(n4, false, new UnionBySize(n4)));
		traversalTest(edges5, new GraphAdjList(n5, false, new UnionBySize(n5)));
		traversalTest(edges6, new GraphAdjList(n6, false, new UnionBySize(n6)));
		traversalTest(edges7, new GraphAdjList(n7, false, new UnionBySize(n7)));

		// -------- Directed Graph Cycle Test Cases --------

		// 8) No Cycle (DAG)
		int n8 = 5;
		int[][] edges8 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } }; // Expected: false

		// 9) Simple Cycle
		int n9 = 4;
		int[][] edges9 = { { 0, 1 }, { 1, 2 }, { 2, 0 } }; // Expected: true

		// 10) Back Edge Cycle
		int n10 = 5;
		int[][] edges10 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 } }; // Expected: true

		// 11) Self Loop
		int n11 = 3;
		int[][] edges11 = { { 0, 1 }, { 1, 2 }, { 2, 2 } }; // Expected: true

		// 12) Disconnected (one has cycle)
		int n12 = 7;
		int[][] edges12 = { { 0, 1 }, { 1, 2 }, { 3, 4 }, { 4, 5 }, { 5, 3 }, { 6, 6 } }; // Expected: true

		// 13) Disconnected (no cycle)
		int n13 = 6;
		int[][] edges13 = { { 0, 1 }, { 1, 2 }, { 3, 4 }, { 4, 5 } }; // Expected: false

		// 14) Cross edges (no cycle)
		int n14 = 5;
		int[][] edges14 = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 } }; // Expected: false

		// 15) Complex Cycle
		int n15 = 6;
		int[][] edges15 = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 }, { 4, 5 } }; // Expected: true

		traversalTest(edges8, new GraphAdjMatrix(n8, true));
		traversalTest(edges9, new GraphAdjMatrix(n9, true));
		traversalTest(edges10, new GraphAdjMatrix(n10, true));
		traversalTest(edges11, new GraphAdjMatrix(n11, true));
		traversalTest(edges12, new GraphAdjMatrix(n12, true));
		traversalTest(edges13, new GraphAdjMatrix(n13, true));
		traversalTest(edges14, new GraphAdjMatrix(n14, true));
		traversalTest(edges15, new GraphAdjMatrix(n15, true));
		
		traversalTest(edges8, new GraphAdjList(n8, true));
		traversalTest(edges9, new GraphAdjList(n9, true));
		traversalTest(edges10, new GraphAdjList(n10, true));
		traversalTest(edges11, new GraphAdjList(n11, true));
		traversalTest(edges12, new GraphAdjList(n12, true));
		traversalTest(edges13, new GraphAdjList(n13, true));
		traversalTest(edges14, new GraphAdjList(n14, true));
		traversalTest(edges15, new GraphAdjList(n15, true));

	}

	private static void traversalTest(int[][] edges, Graph graph) {
		for (int[] edge : edges) {
			graph.addEdge(edge[0], edge[1]);
		}

		// Graph Print
		graph.print();

		System.out.println("BFS cycle detection : " + graph.hasCycleBfs());
		System.out.println("DFS cycle detection : " + graph.hasCycleDfs());
	}

}
