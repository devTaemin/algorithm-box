package com.ssafy.bj.day14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1260 {
	
	static int N, M, V;
	static boolean[] Visited;
	static StringBuilder SB = new StringBuilder();
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	
	public static boolean isAvailable(int node) {
		ArrayList<Integer> connected = adjList.get(node);
		for (int i = 0; i < connected.size(); i++) {
			if (!Visited[i]) return true;
		}
		
		return false;
	}
	
	public static void DFS(int node) {
		SB.append(node).append(" ");
		Visited[node] = true;
		if (!isAvailable(node)) return;
		
		ArrayList<Integer> connected = adjList.get(node);
		Collections.sort(connected);
		for (int i = 0; i < connected.size(); i++) {
			if (Visited[connected.get(i)]) continue;
			DFS(connected.get(i));
		}
	}
	
	public static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		Visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			SB.append(node).append(" ");
			
			ArrayList<Integer> connected = adjList.get(node);
			Collections.sort(connected);
			for (int i = 0; i < connected.size(); i++) {
				if (Visited[connected.get(i)]) continue;
				Visited[connected.get(i)] = true;
				queue.add(connected.get(i));
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i <= N + 1; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		
		Visited = new boolean[N + 1];
		DFS(V);
		SB.append("\n");
		Visited = new boolean[N + 1];
		BFS(V);
		System.out.println(SB);
		
	}

}
