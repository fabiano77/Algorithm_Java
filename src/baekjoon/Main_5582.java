package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main_5582 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		// Scanner sc = new Scanner(System.in);
		
		System.setIn(Main_5582.class.getResourceAsStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//String s1 = sc.next();
		String s1 = br.readLine();
		//String s2 = sc.next();
		String s2 = br.readLine();
		int cnt = 0;
		int result = 0;
		for(int i = 0; i+cnt <= s1.length(); i++) {
			String target = s1.substring(i, i+cnt);
			if(s2.contains(target)) {
				result = cnt;
				cnt++;
				i--;
			}
		}
		System.out.println(result);
		
	}

}
