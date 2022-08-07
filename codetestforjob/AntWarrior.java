package com.ssafy.cfj.dynamic;

import java.util.Scanner;

/**
 * 개미 전사 
 * @author taeminim
 *
 */
public class AntWarrior {
	static int N;
	static int[] Storage;
	static int[] Maximum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Storage = new int[N + 1];
		Maximum = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			Storage[i] = sc.nextInt();
		}
		
		Maximum[1] = Storage[1];
		for (int i = 2; i <= N; i++) {
			Maximum[i] = Math.max(Maximum[i - 1], Maximum[i - 2] + Storage[i]);
		}
		
//		// DEBUG
//		for (int i = 1; i <= N; i++) {
//			System.out.printf("%d ", Maximum[i]);
//		}
		
		System.out.println(Maximum[N]);
	}

}
