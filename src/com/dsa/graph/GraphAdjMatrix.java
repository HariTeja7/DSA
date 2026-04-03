package com.dsa.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.dsa.graph.disjointset.DisJointSet;
import com.dsa.graph.disjointset.UnionByRank;

public class GraphAdjMatrix extends Graph {

	private int[][] adjMatrix;

	public GraphAdjMatrix(int noOfVertices, boolean isDirectional, DisJointSet disJointSet) {
		super(noOfVertices, isDirectional, disJointSet);
		adjMatrix = new int[noOfVertices][noOfVertices];
	}

	public GraphAdjMatrix(int noOfVertices) {
		this(noOfVertices, false, new UnionByRank(noOfVertices));
	}

	public GraphAdjMatrix(int noOfVertices, boolean isDirectional) {
		this(noOfVertices, isDirectional, new UnionByRank(noOfVertices));
	}

	@Override
	public void print() {
		System.out.print("\s\s\s\s");
		for (int i = 0; i < getNoOfVertices(); i++) {
			System.out.printf("%3d", i);
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < getNoOfVertices(); i++) {
			System.out.printf("%3d ", i);
			for (int j = 0; j < getNoOfVertices(); j++) {
				System.out.printf("%3d", adjMatrix[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	public boolean addVertex(int vertex) {
		if (vertex <= getNoOfVertices()) {
			return false;
		} else {
			int newNoOfVertices = vertex - getNoOfEdges();
			int[][] tempGraph = new int[vertex][vertex];
			for (int i = 0; i < getNoOfVertices(); i++) {
				for (int j = 0; j < getNoOfVertices(); j++) {
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

	public boolean edgeExists(int u, int v) {
		return (adjMatrix[u][v] == 1);
	}

	@Override
	public boolean setEdge(int src, int dest) {
		if (src >= getNoOfVertices() && dest >= getNoOfVertices()) {
			return false;
		} else if (src == dest) {
			this.adjMatrix[src][dest] = 1;
		} else {
			this.adjMatrix[src][dest] = 1;
			incrementEdges();
			this.adjMatrix[dest][src] = isDirectional() ? 0 : 1;
		}
		return true;
	}

	@Override
	public Set<Integer> dfsWithRecursion(int src) {
		Set<Integer> visited = new LinkedHashSet<>();
		dfsWithRecursion(src, visited);
		return visited;
	}

	public void dfsWithRecursion(int src, Set<Integer> visited) {
		visited.add(src);
		for (int neighbour = 0; neighbour < getNoOfVertices(); neighbour++) {
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
			for (int neighbour = 0; neighbour < getNoOfVertices(); neighbour++) {
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
			for (int neighbour = 0; neighbour < getNoOfVertices(); neighbour++) {
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
		return getNoOfVertices() == visited.size();
	}

	@Override
	public List<Set<Integer>> components() {
		Set<Integer> visitedNodes = new HashSet<>();
		List<Set<Integer>> componentSet = new LinkedList<>();
		for (int i = 0; i < getNoOfVertices(); i++) {
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
		if (u >= getNoOfVertices() || v >= getNoOfVertices()) {
			return false;
		}
		return pathExistsDfsRecursion(u, v, new HashSet<>());
	}

	private boolean pathExistsDfsRecursion(int u, int v, Set<Integer> visited) {
		if (u == v) {
			return true;
		}
		visited.add(u);
		for (int neighbours = 0; neighbours < getNoOfVertices(); neighbours++) {
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
		if (u >= getNoOfVertices() || v >= getNoOfVertices()) {
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
		for (int neighbours = 0; neighbours < getNoOfVertices(); neighbours++) {
			if (adjMatrix[u][neighbours] == 1 && !visited.contains(neighbours)
					&& pathDfsRecursion(neighbours, v, visited, path)) {
				return true;
			}
		}
		path.remove(Integer.valueOf(u));
		return false;
	}

//####################################################################################################################

	@Override
	protected boolean hasCycleDfsUndirected() {
		boolean[] visited = new boolean[getNoOfVertices()];
		for (int i = 0; i < getNoOfVertices(); i++) {
			if (!visited[i] && hasCycleDfsUndirected(i, -1, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleDfsUndirected(int u, int parent, boolean[] visited) {
		visited[u] = true;
		for (int v = 0; v < getNoOfVertices(); v++) {
			if (adjMatrix[u][v] == 1) {
				if (!visited[v]) {
					if (hasCycleDfsUndirected(v, u, visited)) {
						return true;
					}
				} else if (v != parent) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean hasCycleBFSUndirected() {
		boolean[] visited = new boolean[getNoOfVertices()];
		for (int i = 0; i < getNoOfVertices(); i++) {
			if (!visited[i] && hasCycleBFSUndirected(i, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleBFSUndirected(int current, boolean[] visited) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { current, -1 });
		while (!queue.isEmpty()) {
			int[] pair = queue.poll();
			int u = pair[0];
			int parent = pair[1];
			visited[u] = true;
			for (int v = 0; v < getNoOfVertices(); v++) {
				if (adjMatrix[u][v] == 1) {
					if (!visited[v]) {
						visited[v] = true;
						queue.add(new int[] { v, u });
					} else if (v != parent) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	protected boolean hasCycleDfsColoring() {
		int[] visited = new int[getNoOfVertices()];
		for (int u = 0; u < getNoOfVertices(); u++) {
			if (visited[u] == 0 && hasCycleDfsColoring(u, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleDfsColoring(int u, int[] visited) {
		visited[u] = 2;
		for (int v = 0; v < getNoOfVertices(); v++) {
			if (adjMatrix[u][v] == 1) {
				if (visited[v] == 0) {
					if (hasCycleDfsColoring(v, visited)) {
						return true;
					}
				} else if (visited[v] == 2) {
					return true;
				}
			}
		}
		visited[u] = 1;
		return false;
	}

	@Override
	protected Collection<Integer> sort() {
		int[] inwardDegree = new int[getNoOfVertices()];
		List<Integer> topo = new ArrayList<>(getNoOfVertices());
		Queue<Integer> q = new LinkedList<>();
		fillInwardDegree(inwardDegree);
		for (int u = 0; u < getNoOfVertices(); u++) {
			if (inwardDegree[u] == 0) {
				q.add(u);
			}
		}
		while (!q.isEmpty()) {
			int u = q.poll();
			topo.add(u);
			for (int v = 0; v < getNoOfVertices(); v++) {
				if (this.adjMatrix[u][v] == 1) {
					inwardDegree[v]--;
					if (inwardDegree[v] == 0) {
						q.add(v);
					}
				}
			}
		}
		return topo;
	}

	private void fillInwardDegree(int[] inwardDegree) {
		for (int u = 0; u < getNoOfVertices(); u++) {
			for (int v = 0; v < getNoOfVertices(); v++) {
				if (this.adjMatrix[u][v] == 1) {
					inwardDegree[v]++;
				}
			}
		}
	}

}
