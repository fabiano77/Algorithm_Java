package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_5568 {
	static StringBuilder sb;
	static int N;
	static int K;
	static int[] input;
	static int[] curIdx;
	static List<Integer> isSelectedNum;
	static boolean[] isSelectedIdx;
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		input = new int[N];
		curIdx = new int[K];
		isSelectedNum = new ArrayList<Integer>();
		isSelectedIdx = new boolean[N];
		for(int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		perm(0);
		System.out.println(answer);
	}
	
	private static void perm(int cnt) {
		if(cnt == K) {
			sb = new StringBuilder();
			for(int i = 0; i < K; i++) {
				sb.append(input[curIdx[i]]);
			}
			int num = Integer.parseInt(sb.toString());
			if(!isSelectedNum.contains(num)) {
				answer++;
				isSelectedNum.add(num);
			}
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelectedIdx[i]) continue;
			isSelectedIdx[i] = true;
			curIdx[cnt] = i;
			perm(cnt+1);
			isSelectedIdx[i] = false;
		}
	}

}
