package test_220312;
import java.util.Arrays;

public class Main_2 {

	public static void main(String[] args) {
		for(int[] row : solution(1, false))	System.out.println(Arrays.toString(row));
		System.out.println();
		
		for(int[] row : solution(3, false))	System.out.println(Arrays.toString(row));
		System.out.println();
		
		for(int[] row : solution(6, false))	System.out.println(Arrays.toString(row));
		System.out.println();

	}
	static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //동 남 서 북
	
    static public int[][] solution(int n, boolean clockwise) {
    	// clockwise true -> 시계(동,남,서,북), false-> 반시계(남, 동, 북, 서)
        int[][] answer = new int[n][n];
        
        if(n == 1) {
        	answer[0][0] = 1;
        	return answer;
        }
        
        Point.N = n;
        Point.clockwise = clockwise;
        Point.map = answer;
        
        Point[] points = {
        		new Point(0, 0, 0, 1),
        		new Point(0, n-1, 1, 1),
        		new Point(n-1, n-1, 2, 1),
        		new Point(n-1, 0, 3, 1)
        };
        
        int cnt = 4;
        int endCnt = n*n;
        boolean end = (cnt == endCnt);
        while(!end) {
        	for(Point p : points) {
        		p.next();
        		cnt++;
        		if(cnt == endCnt) {
        			end = true;
        			break;
        		}
        	}
        }
        
        
        return answer;
    }
    static public boolean isIn(int r, int c, int N) {
    	return 0 <= r && r < N && 0 <= c && c < N;
    }
    
    static class Point{
    	int r;
    	int c;
    	int d;
    	int num;
    	static int[][] map;
    	static boolean clockwise;
    	static int N;
		public Point(int r, int c, int d, int num) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.num = num;
			map[r][c] = num;
			if(!clockwise) {
				rotation();
			}
		}
		
		public void next() {
			while(true) {
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				if(!isIn(nr, nc, N) || map[nr][nc] != 0) {
					rotation();
				}
				else {
					r = nr;
					c = nc;
					map[r][c] = ++num;
					break;
				}
			}
			
		}
		
	    private void rotation() {
	    	if(clockwise) {
	    		if(++d == 4) {
	    			d = 0;
	    		}
	    	}
	    	else{
	    		if(--d == -1) {    			 
	    			d = 3;
	    		}
	    	}
	    }
    }
    
}
