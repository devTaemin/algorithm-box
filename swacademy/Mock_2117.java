package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Mock_2117 {
	static int T, N, M, K;
	static int[][] Map;
	static int NumOfHouse;
	
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	public static int countHouse(int row, int col, int k) {
		int result = 0;
		
		// Case: K equals one
		if (k == 1) return 1;
		
		int startRow = row - (k - 1);
		int endRow = row + (k - 1);
		int startCol = col - (k - 1);
		int endCol = col + (k - 1);
		int space = k - 1;
		int check = 1;
		boolean swap = false;
		
		for (int r = startRow; r <= endRow; r++) {
			
			for (int c = startCol + space ; c < startCol + space + check; c++) {
				if (!validateRange(r, c)) {
					continue;
				}
				
				if (Map[r][c] == 1) {
					result++;
				}
			}
			
			if (space == 0) {
				swap = true;
			}
			
			if (!swap) {
				space--;
				check += 2;
			} else {
				space++;
				check -= 2;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream(new File("sample_input.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Map = new int[N + 1][N + 1];
			
			for (int row = 1; row <= N; row++) {
				st = new StringTokenizer(br.readLine());
				for (int col = 1; col <= N; col++) {
					int house = Integer.parseInt(st.nextToken());
					Map[row][col] = house;
					NumOfHouse += house;
				}
			}
			
			int charge = 0;
			while (charge <= NumOfHouse * M) {
				K++;
				charge = (K * K) + (K - 1) * (K - 1);
			}
			
			K -= 1;
			int maxNumberOfHouse = 0;
			
			for (int k = 1; k <= K; k++) {
				charge = (k * k) + (k - 1) * (k - 1);
				for (int row = 1; row <= N; row++) {
					for (int col = 1; col <= N; col++) {
						int numberOfHouse = countHouse(row,col,k);
						
						if (numberOfHouse * M >= charge) {
							maxNumberOfHouse = Math.max(maxNumberOfHouse, numberOfHouse);
						}
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxNumberOfHouse).append("\n");
		}
		
		System.out.println(sb);
	}
}
