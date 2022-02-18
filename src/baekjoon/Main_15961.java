package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15961 {
	static int N;
	static int D;
	static int K;
	static int C;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// N 벨트에 놓인 초밥 접시 수	~3,000,000
		D = Integer.parseInt(st.nextToken());	// D 초밥의 가짓수					~3,000
		K = Integer.parseInt(st.nextToken());	// K 연속해서 먹는 접시의 수		~3,000
		C = Integer.parseInt(st.nextToken());	// C 쿠폰 번호						~3,000
		
		// 초밥 종류
		int[] sushi = new int[D+1];
		
		// 접시의 정보
		int[] dishes = new int[N];
		for(int n = 0; n < N; n++) {
			dishes[n] = Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		// 0번째 접시부터 K개를 관심 영역으로 담는다.
		int kindsNum = 0;
		for(int k = 0; k < K; k++) {
			if(sushi[dishes[k]]++ == 0) {
				kindsNum++;
			}
		}
		
		// 접시를 한바퀴 돌며 탐색한다.
		for(int n = 0; n < N; n++) {
			// 맨 앞것을 빼고
			if(--sushi[dishes[n]] == 0) {
				kindsNum--;
			}
			// 맨 뒤의 것을 추가한다.
			if(n+K >= N) {
				if(sushi[dishes[n+K-N]]++ == 0) {
					kindsNum++;
				}
			}
			else {
				if(sushi[dishes[n+K]]++ == 0) {
					kindsNum++;
				}				
			}
			
			// 쿠폰 적용하여 추가로 먹을수 있다면
			if(sushi[C] == 0) {
				answer = Math.max(answer, kindsNum+1);
			}
			// 현재 관심영역에 쿠폰 스시가 이미 있다면
			else {
				answer = Math.max(answer, kindsNum);				
			}
		}
		System.out.println(answer);

	}

}
