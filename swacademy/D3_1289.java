package com.ssafy.swea.day01;

import java.util.Scanner;

/**
 * 1289. 원재의 메모리 복구하기
 * <pre>
 * https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
 * </pre>
 * @author devTaemin
 *
 */
public class D3_1289 {
	public static int T, Count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/** Number of test case*/
		T = sc.nextInt();
		
		/** For storing results */
		int[] results = new int[T];
		
		/** Looping case*/
		for (int tc = 0; tc < T; tc++) {
			char[] inputs = sc.next().toCharArray();
			char target = '1';
			Count = 0;
			
			/** 
			 * Check if the element of inputs is matching with the target
			 * And switching the target from 0 to 1 and 1 to 0
			 * */
			for (int idx = 0; idx < inputs.length; idx++) {
				if (inputs[idx] == target) {
					if (target == '0') target = '1';
					else target = '0';
					
					Count++;
				}
			}
			
			/** Store the accumulated number of count*/
			results[tc] = Count;
		}
		
		/** Print out the results*/
		for (int tc = 0; tc < T; tc++) {
			System.out.printf("#%d %d%n", tc + 1, results[tc]);
		}
	}
}
