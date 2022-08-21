package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/***
 * 괄호 추가하기
 * @author devTaemin
 *
 */
public class BJ_16637 {
	
	static final int INF = (int)1e9;
	static int N, Maximum;
	static int[] Operands, Sequence;
	static char[] Inputs, Operators;
	
	/***
	 * Calculate result with given array of operands and operators
	 * @param operands
	 * @param operators
	 * @return result
	 */
	public static int calArray(int[] operands, char[] operators) {
		int result = operands[0];
		
		for (int i = 1; i < operands.length; i++) {
			result = calculate(operators[i -1], result, operands[i]);
		}
		
		return result;
	}
	
	/***
	 * Calculate result with given operator, value1, value2
	 * @param operator
	 * @param value1
	 * @param value2
	 * @return result
	 */
	public static int calculate(char operator, int value1, int value2) {
		int result = 0;
		
		switch(operator) {
		case '+':
			result = value1 + value2;
			break;
		case '-':
			result = value1 - value2;
			break;
		case '*':
			result = value1 * value2;
			break;
		}
		
		return result;
	}
	
	
	public static void DFS(int target, int start, int depth, int flag) {
		if (depth == target) {
			int[] copyOperands = Arrays.copyOf(Operands, Operands.length);
			char[] copyOperators = Arrays.copyOf(Operators, Operators.length);
			
			/** Calculate the result with chosen operators first */
			for (int i = 0; i < target; i++) {
				int index = Sequence[i];
				int result = calculate(copyOperators[index], copyOperands[index], copyOperands[index + 1]);
				copyOperands[index] = result;
				
				/** Store INF for used operands */
				copyOperands[index + 1] = INF;
				
				/** Store 'X' for used operators */
				copyOperators[index] = 'X';
			}
			
			/** Store the remaining operands */
			int[] leftOperands = new int[copyOperands.length - target];
			int idx = 0;
			for (int i = 0; i < copyOperands.length; i++) {
				/** Skip used operands*/
				if (copyOperands[i] != INF) {
					leftOperands[idx++] = copyOperands[i];
				}
			}
			
			/** Store the remaining operators */
			char[] leftOperators = new char[copyOperators.length - target];
			idx = 0;
			for (int i = 0; i < copyOperators.length; i++) {
				/** Skip used operators */
				if (copyOperators[i] != 'X') {
					leftOperators[idx++] = copyOperators[i];
				}
			}
			
			/** Compare the local result with the global result */
			Maximum = Math.max(Maximum, calArray(leftOperands, leftOperators));
			return;
		}
		
		/** Bit masking combination */
		for (int i = start; i < Operators.length; i++) {
			if ((flag & 1 << i) != 0) continue;
			
			/** Operators must be chosen is a row (consecutively) */
			if (i >= 1 && (flag & 1 << (i - 1)) != 0) continue;
			
			Sequence[depth] = i;
			DFS(target, i + 1, depth + 1, flag | 1 << i);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Maximum = Integer.MIN_VALUE;
		Inputs = br.readLine().toCharArray();
		Operands = new int[(N + 1) / 2];
		Operators = new char[(N - 1) / 2];
		
		
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				Operands[i / 2] = Inputs[i] - '0';
			} else {
				Operators[(i - 1) / 2] = Inputs[i];
			}
		}
		
		int target = Operands.length / 2;
		for (int i = 1; i <= target; i++) {
			Sequence = new int[i];
			DFS(i, 0, 0, 0);
		}
		
		/** If N == 1, just print out the operand */
		if (N == 1) {
			System.out.println(Operands[0]);
		} else {
			System.out.println(Maximum);
		}
	}
}
