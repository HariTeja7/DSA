package com.dsa.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.dsa.graph.disjointset.DisJointSet;
import com.dsa.graph.disjointset.UnionByRank;

public class GraphAdjList extends Graph {

	private final List<List<Integer>> adjList;

	public GraphAdjList(int noOfVertices, boolean isDirected, DisJointSet disJointSet) {
		super(noOfVertices, isDirected, disJointSet);
		this.adjList = new ArrayList<>();
		for (int i = 0; i < noOfVertices; i++) {
			this.adjList.add(new ArrayList<>());
		}
	}

	public GraphAdjList(int noOfVertices) {
		this(noOfVertices, false, new UnionByRank(noOfVertices));
	}

	public GraphAdjList(int noOfVertices, boolean isDirected) {
		this(noOfVertices, isDirected, new UnionByRank(noOfVertices));
	}

	@Override
	public void print() {
		for (int vertex = 0; vertex < getNoOfVertices(); vertex++) {
			System.out.println(" %3d -> %s".formatted(vertex, adjList.get(vertex)));
		}
	}

	@Override
	public boolean addVertex(int vertex) {
		if (vertex <= getNoOfVertices()) {
			return false;
		} else {
			adjList.add(new ArrayList<>());
			incrementVertices();
			return true;
		}
	}

	@Override
	public boolean edgeExists(int u, int v) {
		return adjList.get(u).contains(v);
	}

	@Override
	public boolean setEdge(int src, int dest) {
		if (src >= getNoOfVertices() && dest >= getNoOfVertices()) {
			return false;
		} else {
			this.adjList.get(src).add(dest);
			incrementEdges();
			if (!isDirectional()) {
				this.adjList.get(dest).add(src);
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
		for (int neighbour : adjList.get(src)) {
			if (!visited.contains(neighbour)) {
				dfsWithRecursion(neighbour, visited);
			}
		}
	}

	@Override
	public Set<Integer> dfs(int src) {
		Set<Integer> visited = new LinkedHashSet<>();
		Deque<Integer> deque = new LinkedList<>();
		deque.addFirst(src);
		while (!deque.isEmpty()) {
			int current = deque.removeFirst();
			visited.add(current);
			for (int neighbour : adjList.get(current)) {
				if (!visited.contains(neighbour)) {
					deque.addFirst(neighbour);
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
			for (int neighbour : adjList.get(current)) {
				if (!visited.contains(neighbour)) {
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
		List<Set<Integer>> componentList = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		for (int vertex = 0; vertex < getNoOfVertices(); vertex++) {
			if (!visited.contains(vertex)) {
				Set<Integer> currentVisited = dfs(vertex);
				componentList.add(currentVisited);
				visited.addAll(currentVisited);
			}
		}
		return componentList;
	}

	@Override
	public boolean pathExists(int u, int v) {
		Set<Integer> visited = new HashSet<>();
		Deque<Integer> stack = new LinkedList<>();
		stack.addFirst(u);
		while (!stack.isEmpty()) {
			int current = stack.removeFirst();
			visited.add(current);
			if (current == v) {
				return true;
			}
			for (int neighbour : adjList.get(current)) {
				if (!visited.contains(neighbour)) {
					stack.addFirst(neighbour);
				}
			}
		}
		return false;
	}

	@Override
	public List<Integer> path(int u, int v) {
		Set<Integer> visited = new HashSet<>();
		List<Integer> path = new ArrayList<>();
		pathWithDfsRecurisive(u, v, visited, path);
		return path;
	}

	private boolean pathWithDfsRecurisive(int u, int v, Set<Integer> visited, List<Integer> path) {
		visited.add(u);
		path.add(u);
		if (u == v) {
			return true;
		}
		for (int neighbour : adjList.get(u)) {
			if (!visited.contains(neighbour) && pathWithDfsRecurisive(neighbour, v, visited, path)) {
				return true;
			}

		}
		path.remove(Integer.valueOf(u));
		return false;
	}

//##############################################################################################################

	@Override
	protected boolean hasCycleDfsUndirected() {
		boolean[] visited = new boolean[getNoOfVertices()];
		for (int u = 0; u < getNoOfVertices(); u++) {
			if (!visited[u] && hasCycleDfsUndirected(u, -1, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleDfsUndirected(int u, int parent, boolean[] visited) {
		visited[u] = true;
		for (int v : adjList.get(u)) {
			if (!visited[v]) {
				if (hasCycleDfsUndirected(v, u, visited)) {
					return true;
				}
			} else if (v != parent) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean hasCycleBFSUndirected() {
		boolean[] visited = new boolean[getNoOfVertices()];
		for (int u = 0; u < getNoOfVertices(); u++) {
			if (!visited[u] && hasCycleBFSUndirected(u, visited)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCycleBFSUndirected(int current, boolean[] visited) {
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[] { current, -1 });
		while (!queue.isEmpty()) {
			Integer[] next = queue.poll();
			int u = next[0];
			int parent = next[1];
			visited[u] = true;
			for (int v : adjList.get(u)) {
				if (!visited[v]) {
					queue.add(new Integer[] { v, u });
				} else if (v != parent) {
					return true;
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
		for (int v : adjList.get(u)) {
			if (visited[v] == 0) {
				if (hasCycleDfsColoring(v, visited)) {
					return true;
				}
			} else if (visited[v] == 2) {
				return true;
			}
		}
		visited[u] = 1;
		return false;
	}

	@Override
	protected Collection<Integer> sort() {
		List<Integer> topo = new ArrayList<>();
		int[] inwardDegree = new int[getNoOfVertices()];
		Queue<Integer> q = new LinkedList<>();
		for (int u = 0; u < getNoOfVertices(); u++) {
			for (int v : adjList.get(u)) {
				inwardDegree[v]++;
			}
		}
		for (int u = 0; u < getNoOfVertices(); u++) {
			if (inwardDegree[u] == 0) {
				q.add(u);
			}
		}
		while (!q.isEmpty()) {
			int u = q.poll();
			topo.add(u);
			for (int v : adjList.get(u)) {
				inwardDegree[v]--;
				if (inwardDegree[v] == 0) {
					q.add(v);
				}
			}
		}
		return topo;
	}

}