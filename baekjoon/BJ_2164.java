package com.ssafy.bj;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 카드 2
 * @author devTaemin
 *
 */
public class BJ_2164 {
	public static int N;
	public static Deque<Integer> deque;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		deque = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			deque.addLast(i);
		}
		
		while (true) {
			/** Check the size of queue */
			if (deque.size() == 1) {
				break;
			}
			
			/** Throw away the top */
			deque.poll();
			
			
			/** Move the next top to the below*/
			int top = deque.poll();
			deque.addLast(top);
		}
		
		/** Print out the last card */
		System.out.println(deque.poll());
	}
}
