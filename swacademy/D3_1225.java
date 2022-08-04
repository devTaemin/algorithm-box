package com.ssafy.swea.day04;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * [S/W 문제해결 기본] 7일차 - 암호생성기
 * @author devTaemin
 *
 */
public class D3_1225 {
	public static Deque<Integer> deque;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			deque = new ArrayDeque<>();
			int temp = sc.nextInt();
			
			/** Store the number in the ArrayDeque*/
			for (int i = 1; i <= 8; i++) {
				deque.addLast(sc.nextInt());
			}
						
			/** play */ 
			int deduct = 0;
			while (true) {
				/** Pop the first item from the queue */
				int number = deque.pollFirst();
				deduct = (deduct % 5) + 1;
				number -= deduct;
				
				/** Termination condition */
				if (number <= 0) {
					number = 0;
					deque.addLast(number);
					break;
				}
				
				/** Move back */
				deque.addLast(number);
			}
			
			/** Save the result */
			sb.append("#" + tc + " ");
			for (int i = 1; i <= 8; i++) {
				sb.append(deque.poll() + " ");
			}
			sb.append("\n");
		}
		
		/** Print out the result */
		System.out.println(sb.toString());
	}

}
