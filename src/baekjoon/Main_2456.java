package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2456 {
	static int N;
	static int[][] counts;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.setIn(Main_2456.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 학생 수 N 입력
		N = Integer.parseInt(br.readLine());
		
		// 회장 후보 3명에 대한 1,2,3 점수 count배열
		// [i][0]에 이름(1, 2, 3)
		// [i][1]에 1점 count
		// ...
		// [i][4]에 총점수
		counts = new int[3][5];
		for(int i = 0; i < 3; i++) {
			counts[i][0] = i+1;
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 3; j++) {
				counts[j][Integer.parseInt(st.nextToken())]++;
			}
		}
		
		// counts배열의 [i][4]번째 값에 sum을 찾음
		for(int i = 0; i < 3; i++) {
			for(int j = 1; j < 4; j++) {
				counts[i][4] += counts[i][j] * (j);
			}
		}
			
		// 정렬
		Arrays.sort(counts, (o1, o2)->{
			if(o1[4]==o2[4]) {
				if(o1[3]==o2[3]) {
					if(o1[2]==o2[2]) {
						return Integer.compare(o1[1], o2[1])*-1;
					}
					else {
						return Integer.compare(o1[2], o2[2])*-1;
					}
				}
				else {
					return Integer.compare(o1[3], o2[3])*-1;
				}
			}
			else {
				return Integer.compare(o1[4], o2[4])*-1;
			}
		});
		
		
		boolean dup = true;
		for(int i = 4; i >= 1; i--) {
			if(counts[0][i] != counts[1][i]) {
				dup = false;
				break;
			}
		}
			
		if(dup) {
			System.out.println(0+" "+counts[0][4]);
		}
		else {
			System.out.println(counts[0][0]+" "+counts[0][4]);
			
		}
		
	}
	

}
