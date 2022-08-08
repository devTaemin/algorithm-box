package com.ssafy.bj.day06;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 요세푸스 문제 
 * @author devTaemin
 *
 */
public class BJ_1158 {
	static int N, K;
	static Deque<Integer> CircularQueue = new ArrayDeque<>(); 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		N = sc.nextInt();
		K = sc.nextInt();
		
		for (int number = 1; number <= N; number++) {
			CircularQueue.add(number);
		}
		
		while (!CircularQueue.isEmpty()) {
			int person = 0;
			
			for (int i = 1; i < K; i++) {
				person = CircularQueue.pollFirst();
				CircularQueue.addLast(person);
			}
			
			person = CircularQueue.pollFirst();
			sb.append(person);
			
			if (!CircularQueue.isEmpty()) {
				sb.append(", ");
			}
		}
		
		sb.append(">");
		
		System.out.println(sb);
	}
}
