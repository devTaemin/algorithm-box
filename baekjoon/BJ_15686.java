package com.ssafy.bj.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/** Node for storing location for house and chicken store */
class Node {
	int row;
	int col;
	
	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}
}

public class BJ_15686 {
	/** Size of the map, Maximum number of chicken store */
	static int N, M;
	
	/** Minimum chicken distance of city */
	static int Minimum;
	
	/** Map */
	static int[][] Map;
	
	/** Location of houses */
	static ArrayList<Node> Houses = new ArrayList<>();
	
	/** Location of chicken stores */
	static ArrayList<Node> Chickens = new ArrayList<>();
	
	/** Distance of chicken road for each house */
	static int[][] Distance;
	
	/** Sequence for combination (DFS) */
	static int[] Sequence;
	
	/** Make combination */
	public static void DFS(int start, int depth) {
		/** If M chicken stores are chosen */
		if (depth == M) {
			int localMinimum = 0;
			int[] eachMin = new int[Houses.size()];
			Arrays.fill(eachMin, (int)1e9);
			for (int i = 0; i < M; i++) {
				int chicken = Sequence[i];
				
				/** Find out the nearest chicken store */
				for (int j = 0; j < Houses.size(); j++) {
					if (eachMin[j] > Distance[j][chicken]) {
						eachMin[j] = Distance[j][chicken];
					}
				}
			}
			
			for (int j = 0; j < Houses.size(); j++) {
				localMinimum += eachMin[j];
			}
			
			
			Minimum = Math.min(Minimum, localMinimum);
			return;
		}
		
		for (int i = start; i < Chickens.size(); i++) {
			Sequence[depth] = i;
			DFS(i + 1, depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		/** Initialize the map */
		Map = new int[N + 1][N + 1];
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine());
			for (int col = 1; col <= N; col++) {
				int type = Integer.parseInt(st.nextToken());
				
				/** check the type of map */
				Map[row][col] = type;
				
				/** Store house */
				if (type == 1) {
					Houses.add(new Node(row, col));
				}
				
				/** Store chicken store */
				if (type == 2) {
					Chickens.add(new Node(row, col));
				}
			}
		}
		
		/** Initialize the distance */
		Distance = new int[Houses.size()][Chickens.size()];
		for (int house = 0; house < Houses.size(); house++) {
			for (int chicken = 0; chicken < Chickens.size(); chicken++) {
				int distance = Math.abs(Houses.get(house).row - Chickens.get(chicken).row) + Math.abs(Houses.get(house).col - Chickens.get(chicken).col);
				Distance[house][chicken] = distance;
			}
		}
		
		/** Initialize the sequence of combination */
		Sequence = new int[M];
		
		/** Initialize the minimum chicken distance */
		Minimum = Integer.MAX_VALUE;
		
		/** Play */
		DFS(0, 0);
		
		/** Print out the result */
		System.out.println(Minimum);
		
	}

}
