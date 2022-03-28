package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1202 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 보석 개수 ~300,000 
		int K = Integer.parseInt(st.nextToken());	// 가방 개수 ~300,000
		
		int[][] jewel = new int [N][2];
		// 보석마다의 무게와 가격 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i][0] = Integer.parseInt(st.nextToken());	//무게
			jewel[i][1] = Integer.parseInt(st.nextToken());	//가격
		}
		// 보석들을 무게를 기준으로 정렬
		Arrays.sort(jewel, (o1, o2)->o1[0] - o2[0]);

		
		int[] bag = new int[K];
		// 가방마다 허용 무게 입력
		for(int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bag);

		// 보석 가격 내림차순
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->o2 - o1);
		
		long ans = 0;
		int j = 0;
		for(int i = 0; i < K; i++) {
			while(j < N && bag[i] >= jewel[j][0]) {
				pq.offer(jewel[j][1]);
				j++;
			}
			
			if(!pq.isEmpty()) ans += pq.poll();
		}
		
		System.out.println(ans);
		

	}

}
