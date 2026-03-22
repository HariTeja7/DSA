package com.dsa.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAdjMatrix extends Graph {

	private int[][] adjMatrix;

	public GraphAdjMatrix(int noOfVertices) {
		super(noOfVertices);
		adjMatrix = new int[noOfVertices][noOfVertices];
	}

	@Override
	public void print() {
		System.out.print("\s\s\s\s\s");
		for (int i = 0; i < noOfVertices; i++) {
			System.out.printf("%3d", i);
		}
		System.out.println();
		for (int i = 0; i < noOfVertices; i++) {
			System.out.printf("%3d->", i);
			for (int j = 0; j < noOfVertices; j++) {
				System.out.printf("%3d", adjMatrix[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	public boolean addVertex(int vertex) {
		if (vertex <= noOfVertices) {
			return false;
		} else {
			int newNoOfVertices = vertex - noOfEdges;
			int[][] tempGraph = new int[vertex][vertex];
			for (int i = 0; i < noOfVertices; i++) {
				for (int j = 0; j < noOfVertices; j++) {
					tempGraph[i][j] = adjMatrix[i][j];
				}
			}
			this.adjMatrix = tempGraph;
			for (int i = 0; i < newNoOfVertices; i++) {
				incrementVertices();
			}
			return true;
		}
	}

	@Override
	public boolean addEdge(int src, int dest, boolean directional) {
		if (src >= noOfVertices && dest >= noOfVertices) {
			return false;
		} else {
			this.adjMatrix[src][dest] = 1;
			incrementEdges();
			if (directional) {
				this.adjMatrix[dest][src] = -1;
			} else {
				this.adjMatrix[dest][src] = 1;
			}
			return true;
		}
	}

	@Override
	public Set<Integer> dfsWithRecursion(int src) {
		Set<Integer> visited = new LinkedHashSet<>();
		dfsWithRecursion(src, visited);
		return visited;
	}

	public void dfsWithRecursion(int src, Set<Integer> visited) {
		visited.add(src);
		for (int neighbour = 0; neighbour < noOfVertices; neighbour++) {
			if (adjMatrix[src][neighbour] == 1 && !visited.contains(neighbour)) {
				dfsWithRecursion(neighbour, visited);
			}
		}
	}

	@Override
	public Set<Integer> dfs(int src) {
		Set<Integer> visited = new LinkedHashSet<>();
		Stack<Integer> stack = new Stack<>();
		stack.add(src);
		while (!stack.isEmpty()) {
			int current = stack.pop();
			visited.add(current);
			for (int neighbour = 0; neighbour < noOfVertices; neighbour++) {
				if (adjMatrix[current][neighbour] == 1 && !visited.contains(neighbour)) {
					stack.add(neighbour);
				}
			}
		}
		return visited;
	}

	@Override
	public Set<Integer> bfs(int src) {
		Set<Integer> visited = new LinkedHashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (!queue.isEmpty()) {
			int current = queue.poll();
			visited.add(current);
			for (int neighbour = 0; neighbour < noOfVertices; neighbour++) {
				if (adjMatrix[current][neighbour] == 1 && !visited.contains(neighbour)) {
					queue.add(neighbour);
				}
			}
		}
		return visited;
	}

	@Override
	public boolean isConnected() {
		Set<Integer> visited = dfs(0);
		return noOfVertices == visited.size();
	}

	@Override
	public List<Set<Integer>> components() {
		Set<Integer> visitedNodes = new HashSet<>();
		List<Set<Integer>> componentSet = new LinkedList<>();
		for (int i = 0; i < noOfVertices; i++) {
			if (!visitedNodes.contains(i)) {
				Set<Integer> visited = dfs(i);
				visitedNodes.addAll(visited);
				componentSet.add(visited);
			}
		}
		return componentSet;
	}

	@Override
	public boolean pathExists(int u, int v) {
		if (u >= noOfVertices || v >= noOfVertices) {
			return false;
		}
		return pathExistsDfsRecursion(u, v, new HashSet<>());
	}

	private boolean pathExistsDfsRecursion(int u, int v, Set<Integer> visited) {
		if (u == v) {
			return true;
		}
		visited.add(u);
		for (int neighbours = 0; neighbours < noOfVertices; neighbours++) {
			if (adjMatrix[u][neighbours] == 1 && !visited.contains(neighbours)
					&& pathExistsDfsRecursion(neighbours, v, visited)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Integer> path(int u, int v) {
		List<Integer> pathList = new ArrayList<>();
		if (u >= noOfVertices || v >= noOfVertices) {
			return pathList;
		}
		List<Integer> path = new LinkedList<>();
		pathDfsRecursion(u, v, new HashSet<>(), path);
		return path;
	}

	private boolean pathDfsRecursion(int u, int v, Set<Integer> visited, List<Integer> path) {
		visited.add(u);
		path.add(u);
		if (u == v) {
			return true;
		}
		for (int neighbours = 0; neighbours < noOfVertices; neighbours++) {
			if (adjMatrix[u][neighbours] == 1 && !visited.contains(neighbours)
					&& pathDfsRecursion(neighbours, v, visited, path)) {
				return true;
			}
		}
		path.remove(Integer.valueOf(u));
		return false;
	}

}
