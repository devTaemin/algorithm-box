package com.ssafy.cfj.dynamic;

import java.util.Scanner;

/**
 * 1로 만들기 
 * @author taeminim
 *
 */
public class MakeOne {
	static int X;
	static int[] Minimum;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
		Minimum = new int[X + 1];
		
		for (int i = 2; i <= X; i++) {
			if (i % 5 == 0) {
				Minimum[i] = Math.min(Minimum[i / 5] + 1, Minimum[i - 1] + 1);
			} else if (i % 3 == 0) {
				Minimum[i] = Math.min(Minimum[i / 3] + 1, Minimum[i - 1] + 1);
			} else if (i % 2 == 0) {
				Minimum[i] = Math.min(Minimum[i / 2] + 1, Minimum[i - 1] + 1);
			} else {
				Minimum[i] = Minimum[i - 1] + 1;
			}
		}
		
//		// DEBUG
//		for (int i = 1; i <= X; i++) {
//			System.out.printf("%d ", Minimum[i]);
//		}
		
		System.out.println(Minimum[X]);
	}

}
