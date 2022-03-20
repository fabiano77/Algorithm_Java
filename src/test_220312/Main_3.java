package test_220312;
import java.util.Arrays;

public class Main_3 {

	public static void main(String[] args) {
//		System.out.println(solution(1, 1, new int[][]{{1, 1}}));
//		System.out.println(solution(1, 2, new int[][]{{1, 1}}));
		System.out.println(solution(2, 2, new int[][]{{1, 1}, {2, 2}}));
//		System.out.println(solution(51, 37, new int[][]{{17, 19}}));

	}
	
    static public int solution(int width, int height, int[][] diagonals) {
        int answer = 0;
        int[][] dp = new int[width+1][height+1];
        for(int i = 0; i <= width; i++) {
        	dp[i][0] = 1;
        }
        for(int i = 0; i <= height; i++) {
        	dp[0][i] = 1;
        }
        
        for(int i = 1; i <= width; i++) {
        	for(int j = 1; j <= height; j++) {
        		dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 10_000_019;
        	}
        }
        
        for(int[] point : diagonals) {
        	// 대각선 시작점 까지 가는 경우의 수
        	int a = dp[point[0]-1][point[1]];
        	// 대각선 끝점 부터의 경우의수
        	int a2 = dp[width-point[0]][height-(point[1]-1)];
        	for(int i = 0; i < a2; i++) {
        		answer = (answer + a) % 10_000_019;
        	}
        	int b = dp[point[0]][point[1]-1];
        	int b2 = dp[width-(point[0]-1)][height-point[1]];
        	for(int i = 0; i < b2; i++) {
        		answer = (answer + b) % 10_000_019;
        	}
        }
        

        return answer;
    }

}
