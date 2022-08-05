package com.ssafy.bj.day05;

import java.util.Scanner;

/**
 * DNA 비밀번호
 * @author devTaemin
 *
 */
public class BJ_12891 {
	static int LenOfStr, LenOfSub, Result;
	static char[] Inputs;
	static int[] CountChar;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		LenOfStr = sc.nextInt();
		LenOfSub = sc.nextInt();
		Inputs = sc.next().toCharArray();
		CountChar = new int[4];
		
		for (int i = 0; i < 4; i++) {
			CountChar[i] = sc.nextInt();
		}
		
		// Sliding Window
		int count = 0;
		for (int i = 0; i < LenOfStr; i++) {
			Add(Inputs[i]);
			count++;
			
			if (count >= LenOfSub) {
				// Check if condition is met
				if (check()) Result++;
				Remove(Inputs[i - count + 1]);
			}
			
		}
		
		System.out.println(Result);
	}
	
	public static boolean check() {
		for (int i = 0; i < 4; i++) {
			if (CountChar[i] > 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void Add(char c) {
		switch(c) {
		case 'A':
			CountChar[0]--;
			break;
		case 'C':
			CountChar[1]--;
			break;
		case 'G':
			CountChar[2]--;
			break;
		case 'T':
			CountChar[3]--;
			break;
		}
	}
	
	public static void Remove(char c) {
		switch(c) {
		case 'A':
			CountChar[0]++;
			break;
		case 'C':
			CountChar[1]++;
			break;
		case 'G':
			CountChar[2]++;
			break;
		case 'T':
			CountChar[3]++;
			break;
		}
	}

}
