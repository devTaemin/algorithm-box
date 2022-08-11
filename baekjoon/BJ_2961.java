package com.ssafy.bj.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/** Ingredient class */
class Ingredient {
	int sour;
	int bitter;
	
	public Ingredient(int sour, int bitter) {
		this.sour = sour;
		this.bitter = bitter;
	}
}

/**
 * 도영이가 만든 맛잇는 음식
 * @author devTaemin
 *
 */
public class BJ_2961 {
	
	/** Number of ingredient, Minimum difference */
	static int N, Diff;
	
	/** Store the result of combination */
	static int[] Sequence;
	
	/** Store the information of ingredients */
	static ArrayList<Ingredient> Array = new ArrayList<>();
	
	/** Make the combination */
	public static void DFS(int target, int start, int depth) {
		if (depth == target) {
			/** Calculate local difference between sourness and bitterness */
			int localSour = 1;
			int localBitter = 0;
			for (int i = 0; i < target; i++) {
				Ingredient igt = Array.get(Sequence[i]);
				localSour *= igt.sour;
				localBitter += igt.bitter;
			}
			
			/** Compare global difference with local difference */
			int localDiff = Math.abs(localSour - localBitter);
			Diff = Math.min(Diff, localDiff);
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			Sequence[depth] = i;
			DFS(target, i + 1, depth + 1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Diff = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			
			Array.add(new Ingredient(sour, bitter));
		}
		
		/** Change the target length from 1 to N */
		for (int target = 1; target <= N; target++) {
			Sequence = new int[target];
			DFS(target, 0, 0);
		}
		
		/** Print out the result */
		System.out.println(Diff);
	}

}
