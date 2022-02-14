package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1038 {
	static int N;
	static int cnt;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 자리수가 1개부터 9개 까지 반복
		for(int i = 1; i <= 10; i++) {
			genNum(i, 0, "");
		}
		
		// 감소하는수의 개수보다 N이 클경우
		if(cnt <= N) System.out.println(-1);

	}
	
	public static void genNum(int digit, int cur, String str) {
		// 원하는 자리수를 다 생성했을경우
		if(digit == cur) {
			// N번째 감소하는 수일 경우 출력
			if(cnt == N) System.out.println(str);
			cnt++;
			return;
		}
		
		// 0부터 9까지 재귀적으로 호출함
		for(int i = 0; i <= 9; i++) {
			// 하지만 직전 string에 담았던 수보다 지금담으려는 수가 클경우 종료한다.
			if(cur>0 && str.charAt(cur-1) <= i+'0') break;
			genNum(digit, cur+1, str+i);
		}
		
	}


}
