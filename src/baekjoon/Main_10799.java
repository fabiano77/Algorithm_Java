package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10799 {
    static int N, K;
    static int[] distance = new int[100001];
    static PriorityQueue<Point> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 100001; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        find();
        System.out.println(distance[K]);
        br.close();
    }

    private static void find() {
        q.offer(new Point(N, 0));
        distance[N] = 0;
        while (!q.isEmpty()) {
            Point curL = q.poll();

            int nx, cost;

            nx = curL.x + 1;
            cost = curL.y + 1;
            if (nx <= 100000 && cost < distance[nx]) {
                distance[nx] = cost;
                q.offer(new Point(nx, cost));
            }

            nx = curL.x - 1;
            cost = curL.y + 1;
            if (0 <= nx && cost < distance[nx]) {
                distance[nx] = cost;
                q.offer(new Point(nx, cost));
            }

            nx = curL.x * 2;
            cost = curL.y;
            if (nx <= 100000 && cost < distance[nx]) {
                distance[nx] = cost;
                q.offer(new Point(nx, cost));
            }

        }
    }
    static class Point implements Comparable<Point>{
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point that) {
            if (this.y > that.y) {
                return 1;
            } else if (this.y < that.y) {
                return -1;
            }
            return 0;
        }
    }
}
