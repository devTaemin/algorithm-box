package com.ssafy.bj.day15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 암호 만들기
 * @author devTaemin
 *
 */
public class BJ_1759 {
	
	/** Length of the cipher, Number of characters */
	static int L, C;
	
	/** Save the result */
	static StringBuilder SB = new StringBuilder();
	
	/** Result of combination */
	static int[] Sequence;
	
	/** Array of characters */
	static String[] Array;
	
	/** make combinations */
	public static void combination(int start, int depth) {
		if (depth == L) {
			int consonant = 0;
			int vowel = 0;
			for (int i = 0; i < L; i++) {
				/** Count the number of vowels */
				if (Array[Sequence[i]].equals("a") ||
					Array[Sequence[i]].equals("e") ||
					Array[Sequence[i]].equals("i") ||
					Array[Sequence[i]].equals("o") ||
					Array[Sequence[i]].equals("u")) {
					vowel++;
				} 
				/** Count the number of consonants */
				else {
					consonant++;
				}
			}
			
			/** If condition is met, save the cipher */
			if (vowel >= 1 && consonant >= 2) {
				for (int i = 0; i < L; i++) {
					SB.append(Array[Sequence[i]]);
				}
				SB.append("\n");
			}
			
			return;
		}
		
		for (int i = start; i < C; i++) {
			Sequence[depth] = i;
			combination(i + 1, depth + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Array = new String[C];
		Sequence = new int[L];
		for (int i = 0; i < C; i++) {
			Array[i] = st.nextToken();
		}
		
		/** Sort in an ascending order */
		Arrays.sort(Array);
		combination(0, 0);
		System.out.println(SB);
	}

}
