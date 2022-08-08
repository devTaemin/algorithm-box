package com.ssafy.cfj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 커리큘럼 
 * @author taeminim
 *
 */
public class Curriculum {
	static int v;
	static int[] indegree = new int[501];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int[] times = new int[501];
	
	public static void topologySort() {
		int[] result = new int[501];
		
		for (int i = 1; i <= v; i++) {
			result[i] = times[i];
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for (int i = 1; i <= v; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			for (int i = 0; i < graph.get(now).size(); i++) {
				int connected = graph.get(now).get(i);
				result[connected] = Math.max(result[connected], result[now] + times[connected]);
				indegree[connected] -= 1;
				
				if (indegree[connected] == 0) {
					q.offer(connected);
				}				
			}
		}
		
		for (int i = 1; i <= v; i++) {
			System.out.println(result[i]);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		v = sc.nextInt();
		
		/** Initialize graph */
		for (int i = 0; i <= v; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		/** Store information of edges */
		for (int i = 1; i <= v; i++) {
			int x = sc.nextInt();
			times[i] = x;
			
			while(true) {
				x = sc.nextInt();
				if (x == -1) break;
				
				/** i has a prerequisite class 'x' */
				indegree[i] += 1;
				
				/**  */
				graph.get(x).add(i);
			}
		}
		
		topologySort();
	}
	
}
