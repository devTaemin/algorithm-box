package com.ssafy.bj.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 트리 순회
 * @author devTaemin
 *
 */
public class BJ_1991 {

	static int N;
	static HashMap<Character, Character[]> Map = new HashMap<>();
	static StringBuilder Sb = new StringBuilder();
	
	/** Pre-order traverse */
	public static void preOrder(char start) {
		char root = start;
		Character[] childs = Map.get(root);
		
		Sb.append(root);
		
		if (childs[0] != '.') {
			preOrder(childs[0]);
		}
		
		if (childs[1] != '.') {
			preOrder(childs[1]);
		}
	}
	
	/** In-Order traverse */
	public static void inOrder(char start) {
		char root = start;
		Character[] childs = Map.get(root);
		
		if (childs[0] != '.') {
			inOrder(childs[0]);
		}
		
		Sb.append(root);
		
		if (childs[1] != '.') {
			inOrder(childs[1]);
		}
	}
	
	/** Post-Order traverse */
	public static void postOrder(char start) {
		char root = start;
		Character[] childs = Map.get(root);
		
		if (childs[0] != '.') {
			postOrder(childs[0]);
		}
		
		if (childs[1] != '.') {
			postOrder(childs[1]);
		}
		
		Sb.append(root);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			Map.put(root, new Character[] { left, right });
		}
		
		preOrder('A');
		Sb.append("\n");
		inOrder('A');
		Sb.append("\n");
		postOrder('A');
		
		/** Print out the result */
		System.out.println(Sb);
	}
	

}
