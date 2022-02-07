package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1497 {
	static int N;
	static int M;
	static int K;
	static String[] data;
	static int[] numbers;
	static int answer = Integer.MAX_VALUE;
	static int songCnt;
	

	public static void main(String[] args) throws IOException{
		System.setIn(Main_1497.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new String[N+1];
		
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			data[n] = st.nextToken();
		}
		
		for(K = 1; K <= M; K++) {
			numbers = new int[K];
			combi(0, 0);
		}

		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == K) {
			boolean[] temp = new boolean[M];
			for(int idx : numbers) {
				for(int i = 0; i < M; i++) {
					if(data[idx].charAt(i) == 'Y') {
						temp[i] = true;
					}
				}
			}
			
			int songTemp = 0;
			for(int i = 0; i < M; i++) {
				if(temp[i] == true) songTemp++;
			}
			if(songTemp > songCnt) {
				songCnt = songTemp;
				answer = K;
			}
			else if (songCnt > 0 && songTemp == songCnt) {
				answer = Math.min(answer, K);				
			}
			return;
		}
		
		for(int i = start; i < N; i++) {
			numbers[cnt] = i;
			combi(cnt+1, i+1);
		}
		
	}
	

}
