package com.ssafy.jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class JO_1828 {
	
	static int N;
	
	static ArrayList<Chemical> Array;
	
	static class Chemical {
		int min;
		int max;
		
		public Chemical(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Array = new ArrayList<Chemical>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Array.add(new Chemical(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(Array, new Comparator<Chemical>() {

			@Override
			public int compare(Chemical o1, Chemical o2) {
				return o1.max - o2.max;
			}
			
		});
		
		int count = 1;
		int max = Array.get(0).max;
		for (int i = 1, size = Array.size(); i < size; i++) {
			Chemical check = Array.get(i);
			if (!(max >= check.min && max <= check.max)) {
				count++;
				max = check.max;
			}
		}
		
		System.out.println(count);
	}

}
