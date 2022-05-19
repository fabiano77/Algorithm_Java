package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2613 {
	
	static List<Integer> ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int max = binSearch(arr, 1, 30000, M);
		List<Integer> alter;
		if(ans.size() < M) {
			int length = ans.size();
			Queue<Integer> q = new LinkedList<>(ans);
			alter = new ArrayList<>(M);
			while(length < M) {
				int num = q.poll();
				while(num > 1 && length < M) {
					num--;
					alter.add(1);
					length++;
				}
				alter.add(num);
			}
			
			alter.addAll(q);
			
		}
		else {
			alter = ans;
		}
		
		
		System.out.println(max);
		alter.forEach((i)->{System.out.print(i+" ");});

	}

	static int binSearch(int[] arr, int start, int end, int m) {
		int ans = -1;
		while (start <= end) {
			int mid = (start + end) / 2;
			
			// 현재 최대값을 mid로해서 m개 이하로 나눠지는 경우
			boolean check = check(arr, mid, m);
			if(check) {
				ans = mid;
				end = mid-1;
			}
			// m개로 안나눠지는 경우
			else {
				start = mid+1;
			}
		}
		return ans;
	}
	
	static boolean check(int[] arr, int max, int m) {
		List<Integer> list = new ArrayList<>();
		int curSum = 0;
		int curIdx = 0;
		for(int num : arr) {
			if(max < num) return false;
			
			curSum += num;
			if(max < curSum) {
				curSum = num;
				list.add(curIdx);
				curIdx = 0;
			}
			curIdx++;
			if(list.size() > m-1) return false;
		}
		list.add(curIdx);
		
		if(list.size() <= m) {
			ans = list;
			return true;
		}
		else
			return false;
	}

}
