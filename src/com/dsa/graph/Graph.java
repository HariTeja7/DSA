package com.dsa.graph;

import java.util.List;
import java.util.Set;

public abstract class Graph {
	
	protected int noOfVertices;
	
	protected int noOfEdges;

	protected Graph(int noOfVertices) {
		this.noOfVertices = noOfVertices;
	}

	public int getNoOfVertices() {
		return noOfVertices;
	}

	public int getNoOfEdges() {
		return noOfEdges;
	}
	
	protected void incrementEdges() {
		this.noOfEdges++;
	}
	
	protected void incrementVertices() {
		this.noOfVertices++;
	}
	
	public abstract boolean addVertex(int vertex);
	
	public boolean addEdge(int u, int v) {
		return addEdge(u, v, false);
	}
	
	public abstract void print();
	
	public abstract boolean addEdge(int u, int v, boolean directional);
	
	//Traversal Algorithms
	public abstract Set<Integer> dfsWithRecursion(int u);
	
	public abstract Set<Integer> dfs(int u);
	
	public abstract Set<Integer> bfs(int u);
	
	//Connected & Component Algorithms
	public abstract boolean isConnected();
	
	public abstract List<Set<Integer>> components();
	
	//Path between nodes
	public abstract boolean pathExists(int u,int v);
	
	public abstract List<Integer> path(int u,int v);
	
}
