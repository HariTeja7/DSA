package com.dsa.graph.disjointset;

public interface DisJointSet {
	
	public int findParent(int u);
	
	public boolean union(int u, int v);

}
