package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16935 {
	static int N, M;
	static int R;
	static String[][] arr2d;

	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/baekjoon/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력 변수 처리
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 2차원 배열 입력 처리
		arr2d = new String[N][M];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				arr2d[n][m] = st.nextToken();
			}
		}
		
		// 연산 명령 입력 처리
		st = new StringTokenizer(br.readLine());
		for(int r = 0; r < R; r++) {
			// 연산 순서대로 실행
			arr2d = operation(arr2d, st.nextToken());			
		}
		
		// 출력 오버헤드를 줄이기 위한 sb
		StringBuilder sb = new StringBuilder();
		for(String[] arr : arr2d) {
			for(String item : arr) {
				sb.append(item).append(" ");
			}
			sb.append("\n");
		}
		
		// 최종 결과 출력
		System.out.println(sb);
		
		
	}
	
	public static String[][] operation(String[][] src, String op) {
		String[][] dst = null;
		int dstN = -1;
		int dstM = -1;
		int mode = -1;
		switch (op) {
		case "1":	// 상하 반전
			dst = new String[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					dst[i][j] = src[(N-1)-i][j];
				}
			}
			break;
			
		case "2":	// 좌우 반전
			dst = new String[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					dst[i][j] = src[i][(M-1)-j];
				}
			}
			break;
			
		case "3":	// 오른쪽 90도 회전
			dstN = M;
			dstM = N;
			dst = new String[dstN][dstM];
			for(int i = 0; i < dstN; i++) {
				for(int j = 0; j < dstM; j++) {
					dst[i][j] = src[(dstM-1)-j][i];
				}
			}
			N = dstN;
			M = dstM;
			break;
			
		case "4":	// 왼쪽 90도 회전
			dstN = M;
			dstM = N;
			dst = new String[dstN][dstM];
			for(int i = 0; i < dstN; i++) {
				for(int j = 0; j < dstM; j++) {
					dst[i][j] = src[j][(dstN-1)-i];
				}
			}
			N = dstN;
			M = dstM;
			break;
			
		case "5":	// 부분배열 시계 회전
			int[][] subArrDeltasRight= {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
			dst = new String[N][M];
			mode = 0;
			for(int r = 0; r < N; r+=N/2) {
				for(int c = 0; c < M; c+=M/2) {
					
					for(int i = 0; i < N/2; i++) {
						for(int j = 0; j < M/2; j++) {
							dst[r+i][c+j] = src[r+i+subArrDeltasRight[mode][0]*(N/2)][c+j+subArrDeltasRight[mode][1]*(M/2)];
						}
					}
					
					mode++;
				}				
			}
			break;
			
		case "6":	// 부분배열 반시계 회전
			int[][] subArrDeltasLeft= {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
			dst = new String[N][M];
			mode = 0;
			for(int r = 0; r < N; r+=N/2) {
				for(int c = 0; c < M; c+=M/2) {
					
					for(int i = 0; i < N/2; i++) {
						for(int j = 0; j < M/2; j++) {
							dst[r+i][c+j] = src[r+i+subArrDeltasLeft[mode][0]*(N/2)][c+j+subArrDeltasLeft[mode][1]*(M/2)];
						}
					}
					
					mode++;
				}				
			}
			break;
			

		default:
			break;
		}
		
		return dst;
	}

}
