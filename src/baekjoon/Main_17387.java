package baekjoon;

import java.io.*;
import java.util.*;


public class Main_17387 {
	
	static class LineSegment {
		public double x1, y1;
		public double x2, y2;
		// 기울기 m과 y절편
		public double m, b;
		private double leftX, xLength;
		private double bottomY, yLength;
		boolean isY;
		
		static LineSegment getInstance(String str) {
			StringTokenizer st = new StringTokenizer(str);
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			return new LineSegment(x1, y1, x2, y2);
		}
		
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

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		LineSegment l1 = LineSegment.getInstance(br.readLine());
		LineSegment l2 = LineSegment.getInstance(br.readLine());
		
		System.out.println(l1.isCross(l2)? 1 : 0);

		

	}

}
