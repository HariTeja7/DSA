package com.dsa.graph.traversal.problems;

class numberOfIslands {
	// https://leetcode.com/problems/number-of-islands/
	public int numIslands(char[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == '1') {
					numIslands(grid, i, j, n, m);
					count++;
				}
			}
		}
		return count;
	}

	private void numIslands(char[][] grid, int i, int j, int n, int m) {
		if (i < 0 || i == n || j < 0 || j == m || grid[i][j] == '0') {
			return;
		}
		grid[i][j] = '0';
		numIslands(grid, i, j + 1, n, m);
		numIslands(grid, i, j - 1, n, m);
		numIslands(grid, i + 1, j, n, m);
		numIslands(grid, i - 1, j, n, m);
	}

}
