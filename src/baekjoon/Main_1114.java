package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1114 {
	static int L;
	static int K;
	static int C;
	static List<Integer> pos;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		L = Integer.parseInt(st.nextToken()); // 통나무의 길이
		K = Integer.parseInt(st.nextToken()); // 자를 수 있는 위치
		C = Integer.parseInt(st.nextToken()); // 자를 수 있는 횟수

		pos = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		pos.add(0);
		for (int k = 0; k < K; k++) {
			pos.add(Integer.parseInt(st.nextToken()));
		}
		pos.add(L);
		Collections.sort(pos);
		// 가장 긴 조각을 작게 만들고, 그 길이를 구해보자

		long maxLen = binSearch(1, 1_000_000_000);
		long first = getFirst(maxLen);
		System.out.println(maxLen + " " + first);
	}

	public static long binSearch(long start, long end) {
		while (start <= end) {
			long mid = (start + end) / 2;
			if(cutByMaxInterval(mid)) {
				end = mid-1;
			}
			else {
				start = mid+1;
			}
		}
		
		return start;
	}
	
	public static long getFirst(long maxLen) {
		int cnt = 0;
		long last = L;
		for(int i = pos.size()-1; i >= 0; i--) {
			if(last - pos.get(i) > maxLen) {
				cnt++;
				last = pos.get(i+1);
			}
		}
		if(cnt < C) {
			last = pos.get(1);
		}
		return last;
	}
	
	public static boolean cutByMaxInterval(long maxLen) {
		int cutCnt = 0;
		int curLen = 0;
		for(int i = 0; i <= K; i++) {
			curLen += pos.get(i+1) - pos.get(i);
			if(curLen > maxLen) {
				cutCnt++;
				curLen = pos.get(i+1) - pos.get(i);
				// 더 긴 최대간격으로 잘라도 됨.
				if(curLen > maxLen) return false;
				if(cutCnt > C) return false;
			}
		}
		return cutCnt <= C;
	}

}