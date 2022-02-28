package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1062 {
	static int N;
	static int K;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] bitWords = new int[N];
		
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			for(int j = 0; j < word.length(); j++) {
				bitWords[i] |= 1 << word.charAt(j)-'a';
			}
		}
		
		// anta**tica
		int needBit = 0b10000010000100000101;
		
		int ans = 0;
		
		// 26개를 뽑는 조합
		for(int i = 0; i < (1<<26); i++) {
			// 26개 중 K개를 뽑는 조합 
			if(Integer.bitCount(i) == K) {
				if((i & needBit) != needBit) continue;
				int cnt = 0;
				for(int j = 0; j < N; j++) {
					if((i & bitWords[j]) == bitWords[j]) cnt++;
				}
				
				ans = Math.max(ans, cnt);
			}
		}
		System.out.println(ans);
		

	}

}
