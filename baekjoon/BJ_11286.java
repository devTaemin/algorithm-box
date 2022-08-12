package com.ssafy.bj.day10;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 절대값 힙
 * @author devTaemin
 *
 */
public class BJ_11286 {
	
	/** Number of commands */
	static int N;
	
	/** PriorityQueue for special min heap */
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	/** Node for containing value and its absolute value*/
	static class Node implements Comparable<Node>{
		public int abs;
		public int value;
		
		public Node(int value) {
			this.abs = Math.abs(value);
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			if (this.abs != o.abs) {
				return Integer.valueOf(this.abs).compareTo(Integer.valueOf(o.abs));
			} else {
				return Integer.valueOf(this.value).compareTo(Integer.valueOf(o.value));				
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		
		/** Loop for commands */
		for (int i = 0; i < N; i++) {
			int value = sc.nextInt();
			
			/** If value is 0 */
			if (value == 0) {
				
				/** If PriorityQueue is empty */
				if (pq.isEmpty()) {
					sb.append(0).append("\n");
				} else {
					sb.append(pq.poll().value).append("\n");
				}
			} else {
				pq.add(new Node(value));
			}
		}
		
		/** Print out the result */
		System.out.println(sb);
	}

}
