package baekjoon;

import java.io.*;
import java.util.*;

public class Main_23792 {
	static int N;
	static int[] han;
	static int[] yang;
	static int[] jung;
	static boolean existHan;
	static boolean existYang;
	static boolean existJung;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		han = new int[N];
		yang = new int[N];
		jung = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			han[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			yang[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			jung[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			int idx = binSearch(1, Integer.MAX_VALUE, a, b, c, k);
			
			if(existHan) {
				sb.append(1);				
			}
			else if(existYang) {
				sb.append(2);
			}
			else {
				sb.append(3);
			}
			sb.append(" ").append(idx).append("\n");
		}
		
		System.out.print(sb);

		

	}
	
	public static int binSearch(int start, int end, int hanEndIdx, int yangEndIdx, int jungEndIdx, int k) {
		// 순서를 찾는 것이 아닌 맛을 찾는 것
		// 임의의 맛에 대해 이분탐색으로 순서를 찾을 수 있음.
		
		while (start <= end) {
			int mid = (int)(((long)start + end) / 2);
			existHan = true;
			existYang = true;
			existJung = true;
			
			// 한식에서 맛mid가 가지는 순서
			int hanIdx = Arrays.binarySearch(han, 0, hanEndIdx, mid);
			if(hanIdx < 0) {
				existHan = false;
				hanIdx = -(hanIdx + 1);
			}
			else {
				hanIdx++;
			}
			
			// 양식에서 맛mid가 가지는 순서
			int yangIdx = Arrays.binarySearch(yang, 0, yangEndIdx, mid);
			if(yangIdx < 0) {
				existYang = false;
				yangIdx = -(yangIdx + 1);
			}
			else {
				yangIdx++;
			}
			
			// 양식에서 맛mid가 가지는 순서
			int jungIdx = Arrays.binarySearch(jung, 0, jungEndIdx, mid);
			if(jungIdx < 0) {
				existJung = false;
				jungIdx = -(jungIdx + 1);
			}
			else {
				jungIdx++;
			}
			
			// 맛 mid가 총 음식에서의 순서
			int order = hanIdx + yangIdx + jungIdx;
			
			// 내가 찾는 k번째이고 한식이나, 양식에 존재하는 맛값일때
			if(order == k && (existHan || existYang || existJung)) {
				if(existHan) {
					return hanIdx;
				}
				else if(existYang){
					return yangIdx;
				}
				else {
					return jungIdx;
				}
			}
			else if(order < k) {
				start = mid+1;
			}
			else {
				end = mid-1;
			}
			
		}
		return -1;
		
	}

}
