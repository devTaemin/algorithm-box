package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ_17281 {
	
	static int Innings, MaxScore;
	static int[] Sequence;
	static boolean[] Visited;
	
	static int[][] Hits;
	
	static int play() {
		int score = 0;
		int seq = 0;
		
		for (int i = 1; i <= Innings; i++) {
			int out = 0;
			boolean[] bases = new boolean[4];
			while (out < 3) {
				seq = seq % 9 + 1;
				int hit = Hits[i][Sequence[seq]];
				
				switch(hit) {
				case 0:
					out++;
					break;
				case 1:
					for (int b = 3; b >= 1; b--) {
						if (bases[b]) {
							bases[b] = false;
							if (b + 1 >= 4) {
								score++;
							} else {
								bases[b + 1] = true;
							}
						}
					}
					bases[1] = true;
					break;
				case 2:
					for (int b = 3; b >= 1; b--) {
						if (bases[b]) {
							bases[b] = false;
							if (b + 2 >= 4) {
								score++;
							} else {
								bases[b + 2] = true;
							}
						}
					}
					bases[2] = true;
					break;
				case 3:
					for (int b = 3; b >= 1; b--) {
						if (bases[b]) {
							bases[b] = false;
							if (b + 3 >= 4) {
								score++;
							} else {
								bases[b + 3] = true;
							}
						}
					}
					bases[3] = true;
					break;
				case 4:
					int count = 1;
					for (int b = 1; b <= 3; b++) {
						if (bases[b]) count++;
						bases[b] = false;
					}
					
					score += count;
					break;
				}
			}
		}
		
		
		return score;
	}
	

	static void permutation(int depth, int flag) {
		if (depth == 10) {	
			MaxScore = Math.max(MaxScore, play());
			return;
		}
		
		for (int i = 2; i <= 9; i++) {
			if ((flag & 1 << i) != 0) continue;
			Sequence[depth] = i;
			if (depth == 3) {
				permutation(depth + 2, flag | 1 << i);
			} else {
				permutation(depth + 1, flag | 1 << i);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Innings = Integer.parseInt(br.readLine());
		Sequence = new int[10];		
		Hits = new int[Innings + 1][10];
		
		for (int i = 1; i <= Innings; i++) {
			st = new StringTokenizer(br.readLine());
			for (int h = 1; h <= 9; h++) {
				Hits[i][h] = Integer.parseInt(st.nextToken());
			}
		}
		
		MaxScore = 0;
		Sequence[4] = 1;
		permutation(1, 1);
		System.out.println(MaxScore);
	}
}
