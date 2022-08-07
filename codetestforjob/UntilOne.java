package com.ssafy.cfj.greedy;

import java.util.Scanner;

/**
 * 1이 될 때까지
 * @author devTaemin
 *
 */
public class UntilOne {
	static int N, K;
	static int[] DP;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		DP = new int[N + 1];
		
		for (int i = 2; i <= N; i++) {
			if (i % K == 0) {
				DP[i] = DP[i / K] + 1;
			} else {
				DP[i] = DP[i - 1] + 1;
			}
		}
		
		System.out.println(DP[N]);
	}

}
