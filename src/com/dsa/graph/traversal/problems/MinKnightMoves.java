package com.dsa.graph.traversal.problems;

import java.util.LinkedList;
import java.util.Queue;

public class MinKnightMoves {

	public int minKnightMoves(int[] knightPos, int[] targetPos, int n) {
		int[][] dist = new int[][] { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
				{ -2, -1 } };
		boolean[][] visited = new boolean[n + 1][n + 1];
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(knightPos, 0));
		visited[knightPos[0]][knightPos[1]] = true;
		while (!queue.isEmpty()) {
			Pair current = queue.poll();
			int[] currentPos = current.d;
			if (currentPos[0] == targetPos[0] && currentPos[1] == targetPos[1]) {
				return current.level;
			}
			for (int[] d : dist) {
				int[] newPos = new int[] { currentPos[0] + d[0], currentPos[1] + d[1] };
				if (newPos[0] >= 1 && newPos[0] <= n && newPos[1] >= 1 && newPos[1] <= n
						&& !visited[newPos[0]][newPos[1]]) {
					visited[newPos[0]][newPos[1]] = true;
					queue.add(new Pair(newPos, current.level + 1));
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		MinKnightMoves m = new MinKnightMoves();
		int n = 6;
		int[] knightPos = new int[] { 4, 5 };
		int[] targetPos = new int[] { 1, 1 };

		int minMoves = m.minKnightMoves(knightPos, targetPos, n);
		System.out.println(minMoves);
	}

}

class Pair {
	int[] d;
	int level;

	public Pair(int[] d, int level) {
		super();
		this.d = d;
		this.level = level;
	}

}
