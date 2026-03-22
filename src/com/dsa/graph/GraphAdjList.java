package com.dsa.graph;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAdjList extends Graph {

	private final List<List<Integer>> adjList;

	@Override
	public void print() {
		for (int vertex = 0; vertex < noOfVertices; vertex++) {
			System.out.println(" %3d -> %s".formatted(vertex, adjList.get(vertex)));
		}
	}

	public GraphAdjList(int noOfVertices) {
		super(noOfVertices);
		this.adjList = new ArrayList<>();
		for (int i = 0; i < noOfVertices; i++) {
			this.adjList.add(new ArrayList<>());
		}
	}

	public boolean addVertex(int vertex) {
		if (vertex <= noOfVertices) {
			return false;
		} else {
			adjList.add(new ArrayList<>());
			incrementVertices();
			return true;
		}
	}

	public boolean addEdge(int src, int dest, boolean directional) {
		if (src >= noOfVertices && dest >= noOfVertices) {
			return false;
		} else {
			this.adjList.get(src).add(dest);
			incrementEdges();
			if (!directional) {
				this.adjList.get(dest).add(src);
			}
			return true;
		}
	}

	@Override
	public boolean addEdge(int src, int dest) {
		return addEdge(src, dest, false);
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
		return noOfVertices == visited.size();
	}

	@Override
	public List<Set<Integer>> components() {
		List<Set<Integer>> componentList = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		for (int vertex = 0; vertex < noOfVertices; vertex++) {
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
		Stack<Integer> stack = new Stack<>();
		stack.add(u);
		while (!stack.isEmpty()) {
			int current = stack.pop();
			visited.add(current);
			if (current == v) {
				return true;
			}
			for (int neighbour : adjList.get(current)) {
				if (!visited.contains(neighbour)) {
					stack.add(neighbour);
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
			if (!visited.contains(neighbour)) {
				if (pathWithDfsRecurisive(neighbour, v, visited, path)) {
					return true;
				}
			}
		}
		path.remove(Integer.valueOf(u));
		return false;
	}

}
