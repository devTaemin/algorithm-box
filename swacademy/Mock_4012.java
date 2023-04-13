package com.ssafy.swea.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 요리사 
 * @author devTaemin
 *
 */
public class Mock_4012 {
	/** Number of test case */
	static int T;
	
	/** Number of ingredients */
	static int N;
	
	/** Map for containing synergy of ingredients */
	static int[][] Map;
	
	/** Total Falvor for a food */
	static int Flavor;
	
	/** Minimum difference between two foods */
	static int Minimum;
	
	/** Sequence for combinations of two ingredients */
	static int[] Sequence;
	
	/** Boolean array for dividing in a half */
	static boolean[] Visited;
	
	/** Calculate the combination of two ingredients for a food */
	public static void calCombination(int[] array, int start, int depth) {
		if (depth == 2) {
			int i = array[Sequence[0]];
			int j = array[Sequence[1]];
			
			Flavor += Map[i][j] + Map[j][i];	
			return;
		}
		
		for (int i = start; i < array.length; i++) {
			Sequence[depth] = i;
			calCombination(array, i + 1, depth + 1);
		}
	}
	
	/** Function to implement DFS 'calCombination' for given array (food) */
	public static int calFlavor(int[] array) {
		Sequence = new int[2];
		calCombination(array, 0, 0);
		
		return Flavor;
	}
	
	/** Root logic for making two teams and calculate their flavors */
	public static void chooseTeam(int start, int depth) {
		if (depth == N / 2) {
			int[] A = new int[N / 2];
			int[] B = new int[N / 2];
			int idxA = 0;
			int idxB = 0;
			for (int i = 1; i <= N; i++) {
				if (Visited[i]) {
					A[idxA++] = i;
				} else {
					B[idxB++] = i;
				}
			}
			
			Flavor = 0;
			int flavorA = calFlavor(A);
			
			Flavor = 0;
			int flavorB = calFlavor(B);
			
			int diff = Math.abs(flavorA - flavorB);
			Minimum = Math.min(Minimum, diff);

			
			return;
		}
		
		for (int i = start; i <= N; i++) {
			if (Visited[i]) continue;
			Visited[i] = true;
			chooseTeam(i + 1, depth + 1);
			Visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N + 1][N + 1];
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					Map[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			Visited = new boolean[N + 1];
			Minimum = Integer.MAX_VALUE;
			chooseTeam(1, 0);
			
			
			sb.append("#").append(tc).append(" ").append(Minimum).append("\n");
			
			
		}
		
		System.out.println(sb);
		
		
	}

}
