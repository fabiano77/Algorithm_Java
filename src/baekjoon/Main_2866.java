package baekjoon;

import java.io.*;
import java.util.*;

public class Main_2866 {
	static int R, C;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		String[] str = new String[R];
		for(int r = 0; r < R; r++) {
			str[r] = br.readLine();
		}
		
		
		// 열로 읽는 문자열을 column개수만큼 새로 생성
		String[] newStr = new String[C];
		StringBuilder sb;
		for(int c = 0; c < C; c++) {
			sb = new StringBuilder();
			// 문자열을 세로로 읽고
			for(int r = 0; r < R; r++) 	sb.append(str[r].charAt(c));
			// 새 변수에 저장
			newStr[c] = sb.toString();
		}
		
		
		
		int count = 0;
		
		// subString을 저장할 임시 String 참조 배열
		String[] tempStr = new String[C];
		exit:
		for(int r = 1; r < R; r++) {
			for(int c = 0; c < C; c++) {
				tempStr[c] = newStr[c].substring(r, R);
			}
			
			// 사전순 정렬
			Arrays.sort(tempStr);

			// 앞에서부터 두개씩 비교
			for(int c = 1; c < C; c++) {
				if(tempStr[c].equals(tempStr[c-1])) {
					break exit;
				}
			}
			count++;
		}

		System.out.println(count);
		

	}

}
