package baekjoon;

import java.io.*;
import java.util.*;

public class Main_19644 {
	static int L, Ml, Mk, C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		L = Integer.parseInt(br.readLine()); // ~ 3,000,000
		st = new StringTokenizer(br.readLine());
		Ml = Integer.parseInt(st.nextToken()); // 유효 거리 ~ 3,000,000
		Mk = Integer.parseInt(st.nextToken()); // 살상력 ~ 100
		C = Integer.parseInt(br.readLine()); // ~ 3,000,000

		int[] road = new int[L];
		for (int l = 0; l < L; l++) {
			road[l] = Integer.parseInt(br.readLine());
		}

		boolean fail = false;
		
		// 거리 마다 기관총을 쐈는지 저장
		boolean[] shot = new boolean[L];
		// 현재 내앞의 좀비가 총맞은 횟수
		int gunCnt = 0;
		
		// 좀비가 다가오는것이 아닌, 기관총 진지가 전진한다고 봐도 무방
		for (int pos = 0; pos < L; pos++) {
			// 유효사거리 지난 총알은 안맞춤
			if(pos - Ml >= 0 && shot[pos-Ml]) {
				gunCnt--;
			}

			// 이전 좀비에게 관통되어서 맞은 데미지 적용
			road[pos] -= Mk * (gunCnt);
			
			// 기관총으로 죽일 수 있다면
			if (road[pos] <= Mk) {
				road[pos] -= Mk;
				shot[pos] = true;
				gunCnt++;
			}
			// 수류탄으로 죽일 수 있다면
			else if (C > 0) {
				C--;
			}
			// 못죽이면 실패
			else {
				fail = true;
				break;
			}
		}

		System.out.println(fail ? "NO" : "YES");

	}

}
