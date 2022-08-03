package com.ssafy.bj.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * 구간 합 구하기4
 * @author devTaemin
 *
 */
public class BJ_11659 {
	public static int N, M, I, J;
	public static int[] Array;
	public static long[] Results;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		/** Index range (1 ~ N) */
		Array = new int[N + 1];
		Results = new long[M + 1];
		
		/** Store the numbers */
		st = new StringTokenizer(br.readLine());
		for (int idx = 1; idx <= N; idx++) {
			Array[idx] = Array[idx - 1];
			Array[idx] += Integer.parseInt(st.nextToken());
		}
		
		/** Play */
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			I = Integer.parseInt(st.nextToken());
			J = Integer.parseInt(st.nextToken());
			
			Results[i] = Array[J] - Array[I - 1];
		}
		
		/** Print out the results */
		for (int i = 1; i <= M; i++) {
			bw.write(Results[i] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}
