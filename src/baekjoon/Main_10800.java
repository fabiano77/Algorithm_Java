package baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main_10800 {
	
	static class Player{
		int color;
		int size;
		public Player(int color, int size) {
			this.color = color;
			this.size = size;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); 		// ~ 200,000
		Player[] players = new Player[N];
		
		int[] sizeCnt = new int[2001];
		ArrayList<Integer>[] colorList = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			colorList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());	// 색
			int size = Integer.parseInt(st.nextToken());	// 크기
			// 사이즈 개수를 센다
			sizeCnt[size]++;
			// 컬러별 리스트에 넣는다.
			colorList[color].add(size);
			players[i] = new Player(color, size);
		}
		
		ArrayList<Integer>[] scoreList = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			// 컬러별 리스트를 정렬한다.
			Collections.sort(colorList[i]);
			scoreList[i] = new ArrayList<>();
			for(int j = 0; j < colorList[i].size(); j++) {
				int score = colorList[i].get(j);
				if(j > 0) score += scoreList[i].get(j-1);
				scoreList[i].add(score);
			}
			
		}
		
		// 사이즈 카운트 배열에 대해
		// 사이즈를 곱한 값을 누적합으로 만든다
		int[] sizePrefixSum = new int[2001];
		for(int i = 1; i <= 2000; i++) {
			sizePrefixSum[i] = (sizeCnt[i] * i) + sizePrefixSum[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for(Player p : players) {

			int cnt = 0;
			cnt += sizePrefixSum[p.size-1];
		
			int idx = leftBinSearch(colorList[p.color], p.size);
			if(idx > 0) cnt -= scoreList[p.color].get(idx-1);
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);


	}
	// 0 1 2 3 4
	// 1 2 3 4 5
	// 3을 찾는다 -> 
	public static int leftBinSearch(List<Integer> list, int key) {
		int start = 0;
		int end = list.size()-1;
		int insPos = 0;
		int retIdx = -1;
		while(start <= end) {
			int mid = (start+end)/2;
			
			if(list.get(mid) == key) {
				retIdx = mid;
				end = mid-1;
			}
			else if(list.get(mid) < key) {
				insPos = mid;
				start = mid+1;
			}
			else {
				end = mid-1;
			}
		}
		if(retIdx>=0) return retIdx;
		return insPos+1;
	}

}
