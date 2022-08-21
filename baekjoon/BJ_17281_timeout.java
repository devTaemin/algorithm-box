package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17281_timeout {
	
	static class Player {
		int number;
		int base;
		ArrayList<Integer> hit;
		
		public Player(int number) {
			this.number = number;
			this.base = 0;
			this.hit = new ArrayList<Integer>();
		}
		
		public void addHit(int h) {
			hit.add(h);
		}
		
		public int getHit(int inn) {
			return hit.get(inn - 1);
		}
		
		public void setBase(int base) {
			this.base = base;
		}
	}
	
	static int Innings, MaxScore;
	static int[] Sequence;
	static boolean[] Visited;
	static ArrayList<Player> Players;
	
//	static int play() {
//		int score = 0;
//		int seq = 0;
//		
//		
//		
//		
//		
//		return score;
//	}
	
	static int play() {
		int score = 0;
		int seq = -1;
		for (int i = 1; i <= Innings; i++) {
			Queue<Player> onTheBase = new LinkedList<Player>();
			boolean[] waitings = new boolean[9];
			Arrays.fill(waitings, true);
			int out = 0;
			while (out < 3) {
				seq = (seq + 1) % 9;
				Player p = Players.get(Sequence[seq]);
				if (!waitings[p.number]) {
					continue;
				}
				
				int hit = p.getHit(i);
				int size = 0;
				
				switch(hit) {
				case(0):
					out++;
					break;	
				case(1):
					onTheBase.offer(p);
					waitings[p.number] = false;
					size = onTheBase.size();
					for (int j = 0; j < size; j++) {
						Player check = onTheBase.poll();
						check.setBase(check.base + 1);
						
						if (check.base >= 4) {
							check.setBase(0);
							score++;
							waitings[check.number] = true;
						} else {
							onTheBase.offer(check);
						}
					}
					break;
				case(2): 
					onTheBase.offer(p);
					waitings[p.number] = false;
					size = onTheBase.size();
					for (int j = 0; j < size; j++) {
						Player check = onTheBase.poll();
						check.setBase(check.base + 2);
						
						if (check.base >= 4) {
							check.setBase(0);
							score++;
							waitings[check.number] = true;
						} else {
							onTheBase.offer(check);
						}
					}
					break;
				case (3): 
					onTheBase.offer(p);
					waitings[p.number] = false;
					size = onTheBase.size();
					for (int j = 0; j < size; j++) {
						Player check = onTheBase.poll();
						check.setBase(check.base + 3);
						
						if (check.base >= 4) {
							check.setBase(0);
							score++;
							waitings[check.number] = true;
						} else {
							onTheBase.offer(check);
						}
					}
					break;
				case(4):
					onTheBase.offer(p);
					waitings[p.number] = false;
					size = onTheBase.size();
					for (int j = 0; j < size; j++) {
						Player check = onTheBase.poll();
						check.setBase(check.base + 4);
						
						if (check.base >= 4) {
							check.setBase(0);
							score++;
							waitings[check.number] = true;
						} else {
							onTheBase.offer(check);
						}
					}
					break;				
				}
			}
			
			while(!onTheBase.isEmpty()) {
				onTheBase.poll().setBase(0);
			}
		}
		
		return score;
	}
	
	
	static void DFS(int depth) {
		
		if (depth == 9) {
//			for (int i = 0; i < 9; i++) {
//				System.out.printf("%d ", Sequence[i]);
//			}
//			System.out.println();
			
			MaxScore = Math.max(MaxScore, play());
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if (Visited[i]) continue;
			Sequence[depth] = i;
			Visited[i] = true;
			if (depth == 2) {
				DFS(depth + 2);
			}else {
				DFS(depth + 1);				
			}
			Visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		Innings = Integer.parseInt(br.readLine());
		Sequence = new int[9];		
		Visited = new boolean[9];
		
		Players = new ArrayList<Player>();
		for (int i = 0; i < 9; i++) {
			Players.add(new Player(i));
		}
		
		for (int i = 0; i < Innings; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				Player p = Players.get(j);
				p.addHit(Integer.parseInt(st.nextToken()));
			}
		}
		
		MaxScore = 0;
		Sequence[3] = 0; 
		DFS(0);
		System.out.println(MaxScore);
	}
}
