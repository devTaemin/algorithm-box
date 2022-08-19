package com.ssafy.bj.day14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_17135 {
	
	static class Pos {
		int id;
		int row;
		int col;
		
		public Pos(int id, int row, int col) {
			this.id = id;
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, M, D, Casualties;
	static int[] Sequence;
	static ArrayList<Pos> Archers = new ArrayList<Pos>();
	static ArrayList<Pos> Original = new ArrayList<Pos>();
	
	public static void play() {
		Queue<Pos> Opponents = new LinkedList<Pos>();
		for (int i = 0; i < Original.size(); i++) {
			int id = Original.get(i).id;
			int row = Original.get(i).row;
			int col = Original.get(i).col;
			
			Opponents.offer(new Pos(id, row, col));
		}
		
		int localCasualties = 0;
		while(!Opponents.isEmpty()) {
			Set<Integer> target = new HashSet<Integer>();
			
			for (int i = 0; i < Archers.size(); i++) {
				Pos archer = Archers.get(i);
				int targetId = 0;
				int targetCol = Integer.MAX_VALUE;
				int targetDistance = Integer.MAX_VALUE;
				boolean found = false;
				
				int size = Opponents.size();
				for (int j = 0; j < size; j++) {
					Pos opps = Opponents.poll();
					
					int distance = (Math.abs(archer.row - opps.row) + Math.abs(archer.col - opps.col));
					if (distance <= D) { 
						if (distance <= targetDistance) {
							if (distance == targetDistance) {
								if (opps.col < targetCol) {
									targetId = opps.id;
									targetCol = opps.col;
									targetDistance = distance;
									found = true;
								}
							} else {
								targetId = opps.id;
								targetCol = opps.col;
								targetDistance = distance;
								found = true;
							}
						}
						
					}
					
					Opponents.offer(opps);
				}
				
				if (found) target.add(targetId);
			}
			
			
			for (int id : target) {
				localCasualties++;
				int size = Opponents.size();
				for (int i = 0; i < size; i++) {
					Pos opps = Opponents.poll();
					if (opps.id != id) {
						Opponents.offer(opps);
					}
				}
			}
			
			int size = Opponents.size();
			for (int i = 0; i < size; i++) {
				Pos opps = Opponents.poll();
				opps.row += 1;
				if (opps.row <= N) { 
					Opponents.offer(opps);
				}
			}
		}
		
		Casualties = Math.max(Casualties, localCasualties);
	}
	
	public static void combination(int start, int depth) {
		if (depth == 3) {
			Archers = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				int col = Sequence[i];
				Archers.add(new Pos(0, N + 1, col));
			}
			
			play();
			
			return;
		}
		
		for (int i = start; i <= M; i++) {
			Sequence[depth] = i;
			combination(i + 1, depth + 1);
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int id = 0;
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= M; c++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					Original.add(new Pos(id++, r, c));
				}
			}
		}
		
		Sequence = new int[3];
		Casualties = 0;
		combination(1, 0);
		System.out.println(Casualties);	
	}

}
