package com.ssafy.bj.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 잃어버린 괄호
 * @author devTaemin
 *
 */
public class BJ_1541 {
	static ArrayList<Integer> Numbers = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		StringTokenizer stForPlus = null;
		
		/** Loop until more tokens are left */
		while (st.hasMoreTokens()) {
			String number = st.nextToken();
			/** Tokenizing the numbers divided by the operator '+' */
			stForPlus = new StringTokenizer(number, "+");
			
			/** Get the result of plus calculation */
			int total = 0;
			while (stForPlus.hasMoreElements()) {
				total += Integer.parseInt(stForPlus.nextToken().trim());
			}
			
			/** Store the numbers */
			Numbers.add(total);
		}
		
		/** Store the first number in result */
		int result = Numbers.get(0);
		
		/** Deduct the numbers */
		for (int i = 1; i < Numbers.size(); i++) {
			result -= Numbers.get(i);
		}
		
		/** Print out the result */
		System.out.println(result);
	}
}
