package com.ssafy.cfj.greedy;

import java.util.Scanner;

/**
 * 숫자 카드 게임
 * @author devTaemin
 *
 */
public class NumberCardGame {
	static int N, M;
	static int Max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		for (int row = 0; row < N; row++) {
			int min = Integer.MAX_VALUE;
			for (int col = 0; col < M; col++) {
				int input = sc.nextInt();
				min = (min < input) ? min : input;
			}
			
			Max = (Max > min) ? Max : min;
		}
		
		System.out.println(Max);
	}

}
