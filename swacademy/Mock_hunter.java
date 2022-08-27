package com.ssafy.swea;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * [A형 연습문제] 헌터
 * @author 	devTaemin
 * @소요시간	80분
 */
public class Mock_hunter {
	
	/** 위치 정보 저장 */
	static class Pos {
		int no;
		int row;
		int col;
		
		public Pos(int no, int row, int col) {
			this.no = no;
			this.row = row;
			this.col = col;
		}
	}
	
	/***
	 * @variable	INF			무한을 나타낸다
	 * @variable	T			테스트 케이스 숫자
	 * @variable	N			맵의 크기
	 * @variable	Time		테스트 케이스 별 최소 시간
	 * @Array		Sequence 	순열 저장 배열
	 * @Array		Dx			행 방향 (북, 동, 남, 서)
	 * @Array		Dy			열 방향 (북, 동, 남, 서)
	 * @2DArray		Map			원본 맵
	 * @2DArray		LocalMap	각 순열 케이스 별로 BFS에서 사용하기 위한 Map
	 * @ArrayList	Numbers		맵 입력 받을 때 고객과 몬스터의 정보를 저장, 순열의 타겟
	 * @HashMap		Units		고객, 몬스터의 정보를 번호로 바로 가져오기 위한 저장 구조
	 */
	static final int INF = (int)1e9;
	static int T, N, Time;
	static int[] Sequence;
	static int[] Dx = { -1, 0, 1, 0 };
	static int[] Dy = { 0, 1, 0, -1 };
	
	static int[][] Map;
	static int[][] LocalMap;
	
	static ArrayList<Integer> Numbers;
	static HashMap<Integer, Pos> Units;
	
	/** 범위 검증 */
	public static boolean validateRange(int row, int col) {
		return (row >= 1 && row <= N && col >= 1 && col <= N);
	}
	
	/** 최단 거리를 구하기 위한 BFS */
	public static int BFS(Pos from, Pos to) {
		/** 최단 거리 테이블 */
		int[][] distance = new int[N + 1][N + 1];
		
		/** 무한으로 초기화 */
		for (int r = 0; r <= N; r++) {
			Arrays.fill(distance[r], INF);
		}
		
		/** BFS를 위한 큐 */
		Queue<Pos> queue = new LinkedList<>();
		/** 시작점 저장 */
		queue.offer(from);
		/** 시작점 거리 0으로 초기화 */
		distance[from.row][from.col] = 0; 
		
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();
			int row = cur.row;
			int col = cur.col;
			
			/** 4 방위 */
			for (int d = 0; d < 4; d++) {
				int checkRow = row + Dx[d];
				int checkCol = col + Dy[d];
				
				/** 범위 검증 */
				if (!validateRange(checkRow, checkCol)) continue;
				
				/** 목표로 하는 몬스터의 공간이 아닌 경우 (다른 몬스터의 위치인 경우) */
				if (LocalMap[checkRow][checkCol] > 0 && LocalMap[checkRow][checkCol] != to.no) continue;
				
				/** 최단 거리 갱신 */
				if (distance[checkRow][checkCol] > distance[row][col] + 1) {
					distance[checkRow][checkCol] = distance[row][col] + 1;
					queue.add(new Pos(0, checkRow, checkCol));
				}
			}
		}
		
		/** 방문한 곳 비우기 */
		LocalMap[to.row][to.col] = 0; 
		
		/** 목표 장소까지의 최단 거리를 리턴 */
		return distance[to.row][to.col];
	}
	
	/** 조건에 맞는 경우의 수 구하기 */
	public static void DFS(int depth, int flag) {
		if (depth == Numbers.size()) {			
			
			/** 처음부터 고객을 만나는 것은 의미가 없다 */
			if (Numbers.get(Sequence[0]) < 0) return;
			
			/** 몬스터를 처치 한 후에 고객을 만나는 것이 의미가 있다. 의미가 있는 순열인지 확인하자 */
			for (int i = 1; i < Numbers.size(); i++) {
				int target = Numbers.get(Sequence[i]);
				
				/** 만약 현재 타겟이 고객이면 */
				if (target < 0) {
					boolean find = false;
					
					/** 고객을 만나기 전에 몬스터를 잡았는지 확인한다 */
					for (int j = 0; j < i; j++) {
						if (Numbers.get(Sequence[j]) == target * -1) {
							find = true;
							break;
						}
					}
					
					/** 잡지 않았으면 종료를 한다. */
					if (!find) return;
				}
				
			}
			
			/** 검증된 순열을 이용해서 몬스터와 고객을 만나면 맵 상에서 없애주기 위해 오리지널 맵을 복사한다 */
			LocalMap = new int[N + 1][N + 1];
			for (int r = 0; r <= N; r++) {
				LocalMap[r] = Arrays.copyOf(Map[r], Map[r].length);
			}
			
			
			/** 시작점 처리 (1,1)를 위해서 따로 해준다 */
			int localTime = 0;
			int time = BFS(new Pos(0, 1, 1), Units.get(Numbers.get(Sequence[0])));
			
			/** 만약 갈 수 없다면 해당 순열은 맵 상에서 적용할 수 없다.*/
			if (time == INF) return;
			/** 갈 수 있다면 현재 케이스의 시간에 누적을 한다 */
			localTime += time;
			
			/** 마찬가지로 검증을 실행한다. */
			for (int i = 0; i < Numbers.size() - 1; i++) {
				time = BFS(Units.get(Numbers.get(Sequence[i])), Units.get(Numbers.get(Sequence[i + 1])));
				if (time == INF) return;
				localTime += time;
			}
			
			/** 전역 시간과 지역 시간을 비교해서 더 작은 것으로 전역 시간을 갱신한다. */
			Time = Math.min(Time, localTime);
			return;
		}
		
		/** flag를 이용한 일반적인 순열방법 */
		for (int i = 0; i < Numbers.size(); i++) {
			if ((flag & 1 << i) != 0) continue;
			Sequence[depth] = i;
			DFS(depth + 1, flag | 1 << i);
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream(new File("sample_input_2.txt")));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			Map = new int[N + 1][N + 1];
			/** 맵을 저장하면서 몬스터와 고객의 번호를 발견하는대로 배열 리스트에 저장한다, 순열의 대상이 된다. */
			Numbers = new ArrayList<Integer>();
			/** 몬스터, 고객의 번호를 키로 사용해서 바로 유닛의 정보를 가져오기 위해 해시맵을 사용한다 */
			Units = new HashMap<Integer, Pos>();
			
			for (int r = 1; r <= N; r++) {
				st = new StringTokenizer(br.readLine().trim(), " ");
				for (int c = 1; c <= N; c++) {
					int type = Integer.parseInt(st.nextToken());
					/** 고객 */
					if (type < 0) {
						Units.put(type, new Pos(type, r, c));
						Numbers.add(type);
					} 
					/** 몬스터 */
					else if (type > 0) {
						Units.put(type, new Pos(type, r, c));
						Numbers.add(type);
					}
					Map[r][c] = type;
				}
			}
			
			/** 순열을 위한 배열 */
			Sequence = new int[Numbers.size()];
			/** 현재 케이스의 최소 시간을 저장하기 위한 변수 */
			Time = Integer.MAX_VALUE;
			DFS(0, 0);
			
			sb.append("#").append(tc).append(" ").append(Time).append("\n");
		}
		
		System.out.println(sb);
	}

}
