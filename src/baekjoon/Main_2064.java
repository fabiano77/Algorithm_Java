package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2064 {
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		long[] ipAddress = new long[N];
		// 0~255 -> 8비트
		
		for(int n = 0; n < N; n++) {
			ipAddress[n] = stringToIp(br.readLine());
		}
		
		long mask = 0;
		
		for(int n = 1; n < N; n++) {
			mask |= ipAddress[n-1] ^ ipAddress[n];
		}
		mask = ((long)1 << 32)-1 & ~mask;			
		for(int i = 31; i >= 0; i--) {
			if((mask & (1 << i)) == 0) {
				
				mask &= ((((long)1 << 32)-1) << i);
				
				break;
			}
		}
		
		long networkAddress = mask & ipAddress[0];
		
		System.out.println(ipToString(networkAddress));
		System.out.println(ipToString(mask));
				
	}

	public static String ipToString(long ipInt) {
		StringBuilder sb = new StringBuilder();
		for(int i = 3; i >= 0; i--) {
			sb.append((ipInt & 255 << (8 * i)) >> (8 * i)).append(".");
		}
		sb.setLength(sb.length()-1);
		
		return sb.toString();
	}
	public static long stringToIp(String ipString) {
		long ipNum = 0;
		st = new StringTokenizer(ipString, ".");
		for(int i = 3; i >= 0; i--) {
			ipNum |= Long.parseLong(st.nextToken()) << (8*i);
		}
		return ipNum;
	}
}
