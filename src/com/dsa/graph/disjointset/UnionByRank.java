package com.dsa.graph.disjointset;

public class UnionByRank implements DisJointSet {

	private int[] rank;

	private int[] parent;

	public UnionByRank(int n) {
		rank = new int[n];
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 1;
			parent[i] = i;
		}
	}

	@Override
	public int findParent(int u) {
		if (parent[u] == u) {
			return u;
		}
		//Path - compression
		parent[u] = findParent(parent[u]);
		return parent[u];
	}

	@Override
	public boolean union(int u, int v) {
		int pu = findParent(u);
		int pv = findParent(v);

		if (pu == pv) {
			return false;
		}

		if (rank[pu] > rank[pv]) {
			parent[pv] = pu;
		} else if (rank[pu] < rank[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
			rank[pu]++;
		}
		return true;
	}

}
