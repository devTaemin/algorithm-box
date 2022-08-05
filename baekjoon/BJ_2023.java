package com.ssafy.bj.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 신기한 소수
 * @author devTaemin
 *
 */
public class BJ_2023 {
	public static int N;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		
		/** Set the first number */
		for (int i = 1; i <= 9; i++) {
			checkNumber(1, String.valueOf(i));			
		}
		
		System.out.println(sb);
	}
	
	public static void checkNumber(int index, String number) {
		if (index == N) {
			if (checkPrime(Integer.parseInt(number))) {
				sb.append(number).append('\n');				
			}
			
			return;
		}
		
		for (int i = 1; i <= 9; i++) {		
			/** First check the given number */
			if (checkPrime(Integer.parseInt(number))) {
				/** If true, check next digit */
				checkNumber(index + 1, number + i);
			} 
		}
	}

	
	/** Check if the number is prime */
	public static boolean checkPrime(int n) {
		if (n == 1) return false;
		
		/** Check until the square root of number */
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		
		return true;
	}

}
