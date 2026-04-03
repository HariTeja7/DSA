package com.dsa.graph;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.dsa.graph.disjointset.DisJointSet;

public abstract class Graph {

	private int noOfVertices;

	private int noOfEdges;

	private boolean isDirectional;

	private DisJointSet disJointSet;

	private boolean hasUndirectionalCycle;

	protected Graph(int noOfVertices, boolean isDirectional, DisJointSet disJointSet) {
		this.noOfVertices = noOfVertices;
		this.isDirectional = isDirectional;
		this.disJointSet = disJointSet;
		this.hasUndirectionalCycle = false;
	}

	public void setNoOfVertices(int noOfVertices) {
		this.noOfVertices = noOfVertices;
	}

	public int getNoOfVertices() {
		return noOfVertices;
	}

	public int getNoOfEdges() {
		return noOfEdges;
	}

	public boolean isDirectional() {
		return isDirectional;
	}

	protected void incrementEdges() {
		this.noOfEdges++;
	}

	protected void incrementVertices() {
		this.noOfVertices++;
	}

	public abstract boolean addVertex(int vertex);

	public boolean addEdge(int u, int v) {
		if (edgeExists(u, v)) {
			return false;
		}
		boolean isEdgeAdded = setEdge(u, v);
		hasUndirectionalCycle = hasUndirectionalCycle
				|| (isEdgeAdded && !isDirectional && !this.disJointSet.union(u, v));
		return isEdgeAdded;
	}

	protected abstract boolean edgeExists(int u, int v);

	protected abstract boolean setEdge(int u, int v);

	public abstract void print();

	// Traversal Algorithms
	public abstract Set<Integer> dfsWithRecursion(int u);

	public abstract Set<Integer> dfs(int u);

	public abstract Set<Integer> bfs(int u);

	// Connected & Component Algorithms
	public abstract boolean isConnected();

	public abstract List<Set<Integer>> components();

	// Path between nodes
	public abstract boolean pathExists(int u, int v);

	public abstract List<Integer> path(int u, int v);

	// Cycle
	public boolean hasCycleDfs() {
		if (isDirectional) {
			return hasCycleDfsColoring();
		}
		return hasCycleDfsUndirected();
	}

	protected abstract boolean hasCycleDfsColoring();

	protected abstract boolean hasCycleDfsUndirected();

	public boolean hasCycleBfs() {
		if (isDirectional) {
			return kahnAlgoBFS();
		}
		return hasCycleBFSUndirected();
	}

	public boolean kahnAlgoBFS() {
		Collection<Integer> sorted = sort();
		System.out.println(sorted);
		return sorted.size() != getNoOfVertices();
	}

	protected abstract boolean hasCycleBFSUndirected();

	public boolean hasCycleUnionFind() {
		return !isDirectional && hasUndirectionalCycle;
	}

	// Sorting
	protected abstract Collection<Integer> sort();

}
