package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2437 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] weight = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			weight[n] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(weight);
		
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(sum+1 < weight[i]) {
				break;
			}
			sum += weight[i];
		}
		
		System.out.println(sum+1);

		

	}

}
