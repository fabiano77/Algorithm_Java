package programmers;

import java.util.*;

public class lv3_카카오_카드짝맞추기 {
	
	public static void main(String[] args) throws CloneNotSupportedException {
		lv3_카카오_카드짝맞추기 instance = new lv3_카카오_카드짝맞추기();
		int[][] board = {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}};
		System.out.println("tc1 : "+instance.solution(board, 1, 0));
	}
	
	static class Card {
		int r, c;
		int num;
		public Card(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + num;
			result = prime * result + r;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Card other = (Card) obj;
			if (c != other.c)
				return false;
			if (num != other.num)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "Card [r=" + r + ", c=" + c + ", num=" + num + "]";
		}
		
		
	}
	
	static class Point implements Cloneable{
		int r, c;
		Card openedCard;
        int solvedCardBit;
        int remaining;
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
        	return super.clone();
        }
		

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + ((openedCard == null) ? 0 : openedCard.hashCode());
			result = prime * result + r;
			result = prime * result + remaining;
			result = prime * result + solvedCardBit;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (c != other.c)
				return false;
			if (openedCard == null) {
				if (other.openedCard != null)
					return false;
			} else if (!openedCard.equals(other.openedCard))
				return false;
			if (r != other.r)
				return false;
			if (remaining != other.remaining)
				return false;
			if (solvedCardBit != other.solvedCardBit)
				return false;
			return true;
		}


		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + ", openedCard=" + openedCard + ", solvedCardBit=" + solvedCardBit
					+ ", remaining=" + remaining + "]";
		}

		
	}
	

    public int solution(int[][] board, int r, int c) throws CloneNotSupportedException {
        int answer = 0;
        int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        Queue<Point> q = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        
        Point start = new Point(r, c);
        start.remaining = getCardCnt(board);
        q.offer(start);
        visited.add(start);
        int time = 0;
        bfs:
        while(!q.isEmpty()) {
        	System.out.println("time = "+time);
        	int qSize = q.size();
        	while(qSize-- > 0) {        		
        		Point cur = q.poll();
				if(cur.r == 0 && cur.c == 0 && cur.solvedCardBit==4) {
					System.out.println("hi");
				}
        		System.out.println(cur);
        		if(cur.remaining == 0) {
        			answer = time;
        			break bfs;
        		}
        		
        		if(board[cur.r][cur.c] != 0 && ((cur.solvedCardBit & (1<<board[cur.r][cur.c])) == 0)) {
        			if(cur.openedCard == null || cur.openedCard != null && !(cur.openedCard.r == cur.r && cur.openedCard.c == cur.c)) {        				
        				int card = board[cur.r][cur.c];
        				Point next = (Point)cur.clone();
        				if (cur.openedCard == null) {
        					next.openedCard = new Card(next.r, next.c, card);
        				} else if(next.openedCard.num == card) {
        					next.solvedCardBit |= (1 << card);
        					next.openedCard = null;
        					next.remaining--;
        				} else {
        					next.openedCard = null;
        				}
        				if(!visited.contains(next)) {
        					q.offer(next);
        					visited.add(next);
        				}
        			}
        		}
        		
        		for(int[] delta : deltas) {
        			int nr = cur.r + delta[0];
        			int nc = cur.c + delta[1];
        			
        			if(isIn(nr, nc)) {
        				Point next = (Point)cur.clone();
        				next.r = nr;
        				next.c = nc;
        				if(!visited.contains(next)) {
        					q.offer(next);
        					visited.add(next);
        				}
        			}
        			
        			for(;;nr+=delta[0], nc+=delta[1]) {
        				if(!isIn(nr, nc)) {
        					nr -= delta[0];
        					nc -= delta[1];
        					break;
        				} else if(board[nr][nc] != 0 && (cur.solvedCardBit & (1 << board[nr][nc])) == 0) {
        					break;
        				}
        			}
        			Point next = (Point)cur.clone();
        			next.r = nr;
        			next.c = nc;
        			if(!visited.contains(next)) {
    					q.offer(next);
    					visited.add(next);
    				}
        		}
        	}
        	time++;
        }        
        
        
        return answer;
    }
    
    public boolean isIn(int r, int c) {
    	return 0 <= r && r < 4 && 0 <= c && c < 4;
    }
    
    public int getCardCnt(int[][] board) {
    	int cnt = 0;
    	for(int i = 0; i < 4; i++) {
    		for(int j = 0; j < 4; j++) {
    			if(board[i][j] != 0) cnt++;
    		}
    	}
    	return cnt / 2;
    }

}
