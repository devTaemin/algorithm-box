package com.ssafy.cfj.shortcut;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 미래 도시 
 * @author taeminim
 *
 */
public class FutureCity {
	static final int INF = 999999;
	static int NumOfCorp, NumOfEdge, Meeting, Destination;
	static Integer[][] Map;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		NumOfCorp = sc.nextInt();
		NumOfEdge = sc.nextInt();
		
		Map = new Integer[NumOfCorp + 1][NumOfCorp + 1];
		
		/** Initialize map */
		for (int row = 1; row <= NumOfCorp; row++) {
			for (int col = 1; col <= NumOfCorp; col++) {
				if (row == col) Map[row][col] = 0;
				else Map[row][col] = INF;
			}
		}
		
		for (int i = 0; i < NumOfEdge; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			Map[from][to] = 1;
			Map[to][from] = 1;
		}
		
		Destination = sc.nextInt();
		Meeting = sc.nextInt();
		
//		// DEBUG
//		for (int i = 1; i <= NumOfCorp; i++) {
//			for (int j = 1; j <= NumOfCorp; j++) {
//				System.out.printf("%6d ", Map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		for (int corp = 1; corp <= NumOfCorp; corp++) {
			for (int i = 1; i <= NumOfCorp; i++) {
				for (int j = 1; j <= NumOfCorp; j++) {
					Map[i][j] = Math.min(Map[i][j], Map[i][corp] + Map[corp][j]);
				}
			}
		}
		
		// DEBUG
		for (int i = 1; i <= NumOfCorp; i++) {
			for (int j = 1; j <= NumOfCorp; j++) {
				System.out.printf("%6d ", Map[i][j]);
			}
			System.out.println();
		}
		
		int result = Map[1][Meeting] + Map[Meeting][Destination];
		result = (result >= INF) ? -1 : result;
		System.out.println(result);
	}
}
