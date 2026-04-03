package com.dsa.graph.disjointset;

public class UnionBySize implements DisJointSet {

	private int[] parent;

	private int[] size;

	public UnionBySize(int n) {
		size = new int[n];
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	@Override
	public int findParent(int u) {
		if (parent[u] == u) {
			return u;
		}
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
		if (size[pu] > size[pv]) {
			parent[pv] = pu;
		} else if (size[pu] < size[pv]) {
			parent[pu] = pv;
		} else {
			parent[pv] = pu;
			size[pu] += size[pv];
		}
		return true;
	}

}
