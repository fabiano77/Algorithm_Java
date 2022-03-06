package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2166 {
	static class Point{
		double x, y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Point[] point = new Point[N];
		
		double a = 0;
		double b = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			point[i] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}
		
		for(int i = 0; i < N-1; i++) {
			a += point[i].x * point[i+1].y;
			b += point[i].y * point[i+1].x;		
		}
		a += point[N-1].x * point[0].y;
		b += point[N-1].y * point[0].x;
		System.out.printf("%.1f",Math.abs(a - b)/2);

		

	}
	


}
