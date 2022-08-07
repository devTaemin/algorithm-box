package com.ssafy.cfj.shortcut;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 전보 
 * @author taeminim
 *
 */
class City {
	int city;
	int time;
	
	public City(int city, int time) {
		this.city = city;
		this.time = time;
	}
}

public class Telegram {
	static final int INF = 1001;
	static int NumOfCity, NumOfEdge, Target;
	static int[] Times;
	static boolean[] Visited;
	static ArrayList<ArrayList<City>> Edge = new ArrayList<ArrayList<City>>();
	
	public static int findNearestCity() {
		int city = -1;
		int time = Integer.MAX_VALUE;
		for (int i = 1; i <= NumOfCity; i++) {
			if (!Visited[i] && Times[i] != INF) {
				if (Times[i] < time) {
					city = i;
				}
			}
		}
		
		return city;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NumOfCity = sc.nextInt();
		NumOfEdge = sc.nextInt();
		Target = sc.nextInt();
		
		Times = new int[NumOfCity + 1];
		Visited = new boolean[NumOfCity + 1];
		
		/** Initialize Times */
		for (int i = 0; i <= NumOfCity; i++) {
			Times[i] = INF;
		}
		
		/** Initialize Edges */
		for (int i = 0; i <= NumOfCity; i++) {
			Edge.add(new ArrayList<City>());
		}
		
		/** Add Edges */
		for (int i = 0; i < NumOfEdge; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int distance = sc.nextInt();
			
			ArrayList<City> e = Edge.get(from);
			e.add(new City(to, distance));
			Edge.set(from, e);
		}
		
		/** Play */
		Times[Target] = 0;
		Visited[Target] = true;
		ArrayList<City> e = Edge.get(Target);
		for (int i = 0; i < e.size(); i++) {
			City c = e.get(i);
			Times[c.city] = Math.min(Times[c.city], Times[Target] + c.time);
		}
		
		for (int i = 0; i < NumOfCity - 1; i++) {
			int index = findNearestCity();
			if (index == -1) break;
			
			Visited[index] = true;
			e = Edge.get(index);
			for (int j = 0; j < e.size(); j++) {
				City c = e.get(i);
				Times[c.city] = Math.min(Times[c.city], Times[index] + c.time);
			}
		}
		
		int count = 0;
		int maxTime = 0;
		for (int i = 1; i <= NumOfCity; i++) {
			int time = Times[i];
			if (time > 0) count++;
			maxTime = Math.max(maxTime, time);
		}
		
		System.out.printf("%d %d%n", count, maxTime);
	}

}
