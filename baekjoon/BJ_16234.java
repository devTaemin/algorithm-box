package com.ssafy.bj.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16234 {

    static int Count, N, L, R;
    static int[][] Map;
    static boolean Found;
    static boolean[][] Visited;

    // North, East, South, West
    static int[] Dx = { -1, 0, 1, 0 };
    static int[] Dy = { 0, 1, 0, -1 };

    static class Nation {
        int row;
        int col;

        public Nation(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        Map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                Map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Count = 0;
        Found = true;
        while (true) {
            // 탐색 플래그 초기화
            Found = false;
            Visited = new boolean[N][N];

            // BFS 탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    BFS(i, j);
                }
            }

            if (Found) {
                Count++;
                // Debug
//                for (int[] line : Map) {
//                    for (int elem : line) {
//                        System.out.print(elem + " ");
//                    }
//                    System.out.println();
//                }
            }
            else break;
        }

        System.out.println(Count);

    }

    public static void BFS(int i, int j) {
        // 중복 제거
        if (Visited[i][j]) return;

        Queue<Nation> queue = new LinkedList<Nation>();
        List<Nation> alliance = new ArrayList<Nation>();
        queue.add(new Nation(i, j));
        Visited[i][j] = true;

        while (!queue.isEmpty()) {
            Nation cur = queue.poll();
            alliance.add(cur);

            for (int d = 0; d < 4; d++) {
                int nextRow = cur.row + Dx[d];
                int nextCol = cur.col + Dy[d];

                if (!validateRange(nextRow, nextCol)) continue;
                if (!Visited[nextRow][nextCol]) {
                    int diff = Math.abs(Map[cur.row][cur.col] - Map[nextRow][nextCol]);
                    if (diff >= L && diff <= R) {
                        queue.add(new Nation(nextRow, nextCol));
                        Visited[nextRow][nextCol] = true;
                    }
                }
            }
        }

        if (alliance.size() == 0 || alliance.size() == 1) return;
        else {
            int size = alliance.size();
            int total = 0;

            for (Nation nation : alliance) {
                total += Map[nation.row][nation.col];
            }

            int result = total / size;
            for (Nation nation : alliance) {
                Map[nation.row][nation.col] = result;
            }

            Found = true;
        }
    }

    public static boolean validateRange(int i, int j) {
        return (i >= 0 && i < N && j >= 0 && j < N);
    }
}
