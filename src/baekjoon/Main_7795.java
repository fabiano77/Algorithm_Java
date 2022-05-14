package baekjoon;

import java.io.*;
import java.util.*;

public class Main_7795 {

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[] arrayA = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {
				arrayA[n] = Integer.parseInt(st.nextToken());
			}
			int[] arrayB = new int[M];
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				arrayB[m] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arrayB);
			int ans = 0;
			for(int a : arrayA) {
				int idx = Arrays.binarySearch(arrayB, a);
				if(idx < 0) {
					idx = -(idx + 1);
				}
				ans += idx;
				
			}
			
			System.out.println(ans);
			
			
		}
		

	}

}
