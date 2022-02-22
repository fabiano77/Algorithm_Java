package baekjoon;

import java.io.*;
import java.util.*;

public class Main_14003 {
	static int N;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 수열의 길이
		N = Integer.parseInt(br.readLine());
		
		// 전체 수열 입력 받기
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		// 가장 긴 부분 수열 저장할 배열
		int[] newArr = new int[N];
		// 원본 배열 요소의 LIS에서의 index를 저장할 배열
		int[] indexes = new int[N];
		// LIS의 길이를 저장할 변수
		int len = 0;
		
		// LIS 생성하기
		for(int i = 0; i < N; i++) {
			// 이분 탐색으로 현재 원소 들어갈 위치 서칭
			int idx = Arrays.binarySearch(newArr, 0, len, arr[i]);
			
			// 음수(bound)라면 들어갈 위치로 보정해준다
			if(idx < 0) idx = -(idx+1);
			
			// 기존 원소보다 커서 새로 삽입되는 요소
			if(idx == len) {
				newArr[idx] = arr[i];
				len++;
			}
			// 기존 원소보다 작아서 사이로 대체될 수 있는 요소
			else {
				newArr[idx] =arr[i];
			}
			// LIS에서의 인덱스를 기록한다.
			indexes[i] = idx;
		}
		
		// 위의 LIS는 훼손됐으므로 indexes 배열로 복원한다.
		int tempLen = len;
		// 가장 뒤에서부터 반복한다.
		for(int i = N-1; i >= 0; i--) {
			// 정해지지 않은 마지막에 와야할 원소들을 반복적으로 찾는다.
			if(indexes[i] == tempLen-1) {
				newArr[tempLen-1] = arr[i];
				tempLen--;
			}
		}
		
		// string builder를 이용한 출력 4928ms->708ms
		sb.append(len).append("\n");
		for(int i = 0; i < len; i++) sb.append(newArr[i]).append(" ");
		
		System.out.println(sb);



	}

}
