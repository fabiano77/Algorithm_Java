package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1213 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String str = br.readLine();
		
		int[] arr = new int['Z'-'A'+1];
		for(char c : str.toCharArray()) {
			arr[c-'A']++;
		}
		
		int odd = 0;
		int oddIdx = -1;
		int even = 0;
		
		for(int i = 0; i <= 'Z'-'A'; i++) {
			if(arr[i] % 2 == 0) {
				even++;
			}
			else {
				odd++;
				oddIdx = i;
			}
		}
		
		if(odd>1) {
			System.out.println("I'm Sorry Hansoo");
		}
		else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i <= 'Z'-'A'; i++) {
				int cnt = arr[i] / 2;
				while(cnt-- > 0) {
					sb.append((char)('A'+i));
				}
			}
			if(odd>0) {
				sb.append((char)('A'+oddIdx));
			}
			for(int i = 'Z'-'A'; i >= 0; i--) {
				int cnt = arr[i] / 2;
				while(cnt-- > 0) {
					sb.append((char)('A'+i));
				}
			}
			System.out.println(sb);
		
		}
				
		

	}

}
