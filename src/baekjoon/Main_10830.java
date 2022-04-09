package baekjoon;

import java.io.*;
import java.util.*;

public class Main_10830 {
	
	static class Mat {
		static int N;
		int[][] components;
		
		public Mat() {
			this.components = new int[N][N];
		}
		
		public Mat multiply(Mat o) {
			Mat result = new Mat();
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					//result의 r행c열 요소에 대입
					for(int t = 0; t < N; t++) {
						result.components[r][c] = (result.components[r][c] + this.components[r][t] * o.components[t][c])%1000;
					}
				}
			}
			return result;
		}
		
		public void print() {
			StringBuilder sb = new StringBuilder();
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					sb.append(components[r][c]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
		
		public boolean isZero() {
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					if(components[r][c] != 0) return false;
				}
			}
			return true;
		}
		
		public static Mat pow(Map<Long, Mat> matrices, long b) {
			if(matrices.get(b) != null) {
				return matrices.get(b);
			}
			
			long half1 = b/2;
			long half2 = b - half1;
			matrices.put(b, pow(matrices, half1).multiply(pow(matrices, half2)));
			
			return matrices.get(b);
		}
		
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		Mat.N = N;
		Map<Long, Mat> matrixPow = new HashMap<>();

		matrixPow.put(1L, new Mat());
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				matrixPow.get(1L).components[r][c] = (Integer.parseInt(st.nextToken())) % 1000;
			}
		}
		
		Mat.pow(matrixPow, B).print();
	}

}
