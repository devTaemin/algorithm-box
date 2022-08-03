package com.ssafy.bj.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기 5
 * @author devTaemin
 *
 */
public class BJ_11660 {
	/** Size of map, Number of calculation */
	public static int N, M;
	
	/** Indices */
	public static int X1, Y1, X2, Y2;
	
	/** List for storing results */
	public static int[] Result;
	
	/** Map */
	public static int[][] Map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/** Range (1 ~ N) */
		Result = new int[M + 1];
		Map = new int[N + 1][N + 1];
		
		/** Store the numbers */
		/** Accumulate the number in rows */
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			
			for (int col = 1; col <= N; col++) {
				Map[row][col] = Map[row][col - 1];
				Map[row][col] += Integer.parseInt(st.nextToken());
			}
		}
		
		/** Play */
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			X1 = Integer.parseInt(st.nextToken());
			Y1 = Integer.parseInt(st.nextToken());
			X2 = Integer.parseInt(st.nextToken());
			Y2 = Integer.parseInt(st.nextToken());

			/**
			 * Accumulate the difference between the element in the column of 'Y2' and 'Y2 - 1' by rows
			 *  */
			for (int row = X1; row <= X2; row++) {
				Result[i] += (Map[row][Y2] - Map[row][Y1 -1]);
			}
			
			bw.write(Result[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
