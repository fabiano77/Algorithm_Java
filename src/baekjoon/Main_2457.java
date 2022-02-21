package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2457 {
	static int N;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] flower = new int[N][2]; 
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			
			flower[n][0] = dateTo365(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			flower[n][1] = dateTo365(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(flower, (o1, o2)->{
			if(o1[0] == o2[0]) return o2[1] - o1[1];
			return o1[0]-o2[0];
		});
		
//		for(int[] fl : flower) {
//			System.out.println(Arrays.toString(fl));
//		}

		int lastDay = dateTo365(3, 1);
		int endDay = dateTo365(11, 30);
		
		int floIdx = 0;
		int longestDay = 0;
		int cnt = 0;
		while(lastDay <= endDay && floIdx < N) {
			boolean out = true;
			while(floIdx < N && flower[floIdx][0] <= lastDay) {
				out = false;
				longestDay = Math.max(longestDay, flower[floIdx][1]);
				floIdx++;
			}
			if(out) break;
			lastDay = longestDay;
			cnt++;
		}
		
		if(lastDay > endDay) {
			System.out.println(cnt);			
		}
		else {
			System.out.println(0);
		}
		

	}
	
	static int dateTo365(int mon, int day){
		int retDays = 0;
		
		for(int m = 1; m < mon; m++) {
			if(m == 2) {
				retDays += 28;
			}
			else if(m == 4 || m == 6 || m == 9 || m == 11) {
				retDays += 30;
			}
			else {
				retDays += 31;
			}
		}
		return retDays + day;
		
	}

}
