package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 숫자 만들기 
 * @author devTaemin
 *
 */
public class Mock_4008 {
	/** Number of test case*/
	static int T;
	
	/** Number of input numbers */
	static int N;
	
	/** Maximum result, Minimum result */
	static int Maximum, Minimum;
	
	/** Type of operators */
	static char[] Operators;
	
	/** Storage for input numbers */
	static int[] Numbers;
	
	/** Storage for sorted order of operators */
	static int[] Sequence;
	
	/** Check visited */
	static boolean[] Visited;
	
	/** Initialize an operator list*/
	public static void initOperators(int plus, int minus, int mul, int div) {
		int idx = 0;
		for (int i = 0; i < plus; i++) {
			Operators[idx++] = '+';
		}
		for (int i = 0; i < minus; i++) {
			Operators[idx++] = '-';
		}
		for (int i = 0; i < mul; i++) {
			Operators[idx++] = '*';
		}
		for (int i = 0; i < div; i++) {
			Operators[idx++] = '/';
		}
	}
	
	/** Permutation */
	public static void DFS(int depth) {
		if (depth == N - 1) {
			int result = Numbers[0];
			for (int i = 1; i < N; i++) {
				char operator = Operators[Sequence[i - 1]];
				
				switch(operator) {
				case '+':
					result += Numbers[i];
					break;
				case '-':
					result -= Numbers[i];
					break;
				case '*':
					result *= Numbers[i];
					break;
				case '/':
					if (Numbers[i] == 0) return;
					
					result /= Numbers[i];
					break;
				}
			}
			
			Minimum = Math.min(Minimum, result);
			Maximum = Math.max(Maximum, result);			
			return;
		}
		
		for (int i = 0; i < N - 1; i++) {
			if (Visited[i]) continue;
			
			/** Skip duplicated permutation */
			for (int j = 0; j < depth; j++) {
				if (Sequence[j] > i && Operators[Sequence[j]] == Operators[i]) {
					return;
				}
			}
			
			Sequence[depth] = i;
			Visited[i] = true;
			DFS(depth + 1);
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
			Maximum = Integer.MIN_VALUE;
			Minimum = Integer.MAX_VALUE;
			
			Operators = new char[N - 1];
			Numbers = new int[N];
			Sequence = new int[N - 1];
			Visited = new boolean[N - 1];
			
			st = new StringTokenizer(br.readLine());
			int numOfPlus = Integer.parseInt(st.nextToken());
			int numOfMinus = Integer.parseInt(st.nextToken());
			int numOfMul = Integer.parseInt(st.nextToken());
			int numOfDiv = Integer.parseInt(st.nextToken());
			
			initOperators(numOfPlus, numOfMinus, numOfMul, numOfDiv);
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			DFS(0);
			
			sb.append("#").append(tc).append(" ").append(Maximum - Minimum).append("\n");
		}
		
		System.out.println(sb);
	}
}
