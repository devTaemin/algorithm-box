package com.ssafy.cfj.dynamic;

import java.util.Scanner;

/**
 * 바닥 공사  
 * @author taeminim
 *
 */
public class ConstructFloor {
	static int N;
	static int[] Count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		if (N == 1) {
			System.out.println(1);
			return;
		}
		
		if (N == 2) {
			System.out.println(3);
			return;
		}
		
		/** Range from 0 to N */
		Count = new int[N + 1];
		Count[1] = 1;
		Count[2] = 3;
		
		// N >= 3
		for (int i = 3; i <= N; i++) {
			Count[i] = (Count[i - 2] * 2 + Count[i - 1]) % 796796;			
		}
		
//		// DEBUG
//		for (int i = 1; i <= N; i++) {
//			System.out.printf("%d ", Count[i]);
//		}
		
		
		System.out.println(Count[N]);
	}
}
