package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2162 {
	
	static class LineSegment {
		public double x1, y1;
		public double x2, y2;
		// 기울기 m과 y절편
		public double m, b;
		private double leftX, xLength;
		private double bottomY, yLength;
		boolean isY;
		
		public LineSegment(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			m = (double)(y1-y2)/(x1-x2);
			b = y1-m*x1;
			leftX = Math.min(x1, x2);
			xLength = Math.abs(x1 - x2);
			bottomY = Math.min(y1, y2);
			yLength = Math.abs(y1 - y2);
			if(!Double.isFinite(m)) isY = true;
		}

		


		public boolean isCross(LineSegment o) {
			double x, y;
			if(Double.isFinite(this.m) && this.m == o.m) {
				if(this.b == o.b) {
					double left, leftLen, right;
					if(this.leftX < o.leftX) {
						left = this.leftX;
						leftLen = this.xLength;
						right = o.leftX;
					}
					else {
						left = o.leftX;
						leftLen = o.xLength;
						right = this.leftX;
					}
					return left+leftLen >= right;
				}
				return false;
			}
			else if(this.m == 0 && o.m == 0) {
				if(this.y1 == o.y1) {
					double left, leftLen, right;
					if(this.leftX < o.leftX) {
						left = this.leftX;
						leftLen = this.xLength;
						right = o.leftX;
					}
					else {
						left = o.leftX;
						leftLen = o.xLength;
						right = this.leftX;
					}
					return left+leftLen >= right;
				}
				return false; 
			}
			else if(this.isY && o.isY) {
				if(this.x1 == o.x1) {
					double bottom, bottomLen, top;
					if(this.bottomY < o.bottomY) {
						bottom = this.bottomY;
						bottomLen = this.yLength;
						top = o.bottomY;
					}
					else {
						bottom = o.bottomY;
						bottomLen = o.yLength;
						top = this.bottomY;
					}
					return bottom+bottomLen >= top;
				}
				return false; 
			}
			else if(this.isY) {
				x = this.x1; 
				y = o.m * x + o.b;
			}
			else if(o.isY) {
				x = o.x1; 
				y = this.m * x + this.b;
			}
			else {
				x = (o.b - this.b) / (this.m - o.m);
				y = this.m * x + b;		
			}
					
			if(isIn(x, y) && o.isIn(x, y)) {
				return true;
			}
			return false;
		}
		
		private boolean isIn(double x, double y) {
			x = Math.round(x*100000)/100000.0;
			y = Math.round(y*100000)/100000.0;
			return leftX <= x && x <= leftX+xLength && bottomY <= y && y <= bottomY + yLength;
		}
		
		
	}
	
	static int N;
	static int[] parent;
	static int[] size;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		size = new int[N];
		
		for(int i = 0; i < N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		LineSegment[] lines = new LineSegment[N];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			lines[n] = new LineSegment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
										Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = i; j < N; j++) {
				if(lines[i].isCross(lines[j])) {
					if(find(i) != find(j)) {
						union(i, j);
					}
				}
			}
		}

		
		int cnt = 0;
		int big = 0;
		for(int i = 0; i < N; i++) {
			if(size[i] != 0) {
				cnt++;
				big = Math.max(big, size[i]);
			}
		}
		
		System.out.print(cnt+"\n"+big);
	}
	
	public static int find(int a) {
		if(parent[a] == a) {
			return a;
		}	
		return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if(parentA < parentB) {
			parent[parentB] = parentA;
			size[parentA] += size[parentB];
			size[parentB] = 0;
		}
		else {
			parent[parentA] = parentB;
			size[parentB] += size[parentA];
			size[parentA] = 0;
		}
		
	}

}
