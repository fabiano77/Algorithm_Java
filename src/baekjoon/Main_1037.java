package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1037 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
        
		while(T-- > 0) {
			int N = Integer.parseInt(st.nextToken());
			max = Math.max(N, max);
			min = Math.min(N, min);
		}
		System.out.println(max * min);

	}

}
