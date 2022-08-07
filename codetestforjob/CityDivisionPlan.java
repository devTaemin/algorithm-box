package com.ssafy.cfj.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 도시 분할 계획 
 * @author taeminim
 *
 *
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
 */
class Road implements Comparable<Road> {
	private int from;
	private int to;
	private int cost;
	
	public Road(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
	
	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Road o) {
		return Integer.valueOf(this.getCost()).compareTo(Integer.valueOf(o.getCost()));
	}
}

public class CityDivisionPlan {
	
	static int NumOfHouse, NumOfRoad;
	static ArrayList<Road> Roads = new ArrayList<>();
	static int[] Parent;
	
	public static int findParent(int child) {
		if (Parent[child] != child) {
			Parent[child] = findParent(Parent[child]);
		}
		
		return Parent[child];
	}
	
	public static void unionParent(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if (pa < pb) {
			Parent[b] = pa;
		} else {
			Parent[a] = pb;
		}
	}
	
	public static boolean isCycle(int a, int b) {
		int pa = findParent(a);
		int pb = findParent(b);
		
		if (pa == pb) return true;
		else return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		NumOfHouse = sc.nextInt();
		NumOfRoad = sc.nextInt();
		
		/** Initialize parents */
		Parent = new int[NumOfHouse + 1];
		for (int i = 0; i <= NumOfHouse; i++) {
			Parent[i] = i;
		}
		
		/** Store the roads */
		for (int i = 0; i < NumOfRoad; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int cost = sc.nextInt();
			Roads.add(new Road(from, to, cost));
		}
		
		/** Sort in an ascending order according to the cost */
		Collections.sort(Roads);
		
		/** Play */
		int count = 0;
		int totalCost = 0;
		for (int i = 0; i < NumOfRoad; i++) {
			Road road = Roads.get(i);
			int from = road.getFrom();
			int to = road.getTo();
			int cost = road.getCost();
			
			if (!isCycle(from, to)) {
				unionParent(from, to);
				totalCost += cost;
				count++;
			}
			
			if (count == NumOfHouse - 2) {
				break;
			}
		}
		
		System.out.println(totalCost);
	}

}
