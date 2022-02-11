package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_3020 {
	static int N;
	static int H;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		int half = N/2;
		
		int[] arrUp = new int[N/2+N%2];
		Integer[] arrDown = new Integer[N/2];
		int upIdx = 0;
		int downIdx = 0;
		for(int n = 0; n < N; n++) {
			if(n%2==0) arrUp[upIdx++] = Integer.parseInt(br.readLine());
			else arrDown[downIdx++] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arrDown, Collections.reverseOrder());
		Arrays.sort(arrUp);
		
		int minBreak = Integer.MAX_VALUE;
		int minCnt = 0;
		int breakCnt = arrUp.length;
		upIdx = 0;
		downIdx = 0;
		for(int i = 1; i <= H; i++) {
			while(upIdx < arrUp.length && i > arrUp[upIdx]) {
				breakCnt--;
				upIdx++;
			}
			while(downIdx < arrDown.length && i > (H-arrDown[downIdx])) {
				breakCnt++;
				downIdx++;
			}
			if(minBreak > breakCnt) {
				minCnt = 1;
				minBreak = breakCnt;
			} else if(minBreak == breakCnt) {
				minCnt++;
			}
		}
		System.out.println(minBreak+" "+minCnt);
	}

}
