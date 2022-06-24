package programmers;

public class lv3_카카오_자물쇠와열쇠 {
	static int M;
    static int N;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        // 비어있는 곳이 없어야 한다.
        M = key.length;
        N = lock.length;
        
        int index = 2;
        solve:
        for(int d = 0; d < 4; d++) {
            key = rotate(key);
            
            for(int keyR = -(M-1); keyR <= N-1; keyR++){
                next:
                for(int keyC = -(M-1); keyC <= N-1; keyC++){
                    index++;
                    for(int r = 0; r < M; r++){
                        for(int c = 0; c < M; c++){
                            if(isIn(keyR+r, keyC+c)){
                                if((key[r][c] == 1 && lock[keyR+r][keyC+c] == 1) ||
                                   (key[r][c] == 0 && lock[keyR+r][keyC+c] == 0)) {
                                    continue next;
                                }
                                else if(key[r][c] == 1){
                                    lock[keyR+r][keyC+c] = index;
                                }
                            }
                        }
                    }
                    if(check(lock, index)){
                        answer = true;
                        break solve;
                    }
                }
            }
        }
        
        return answer;
    }
    
    public boolean check(int[][] lock, int index) {
        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                if(lock[r][c] != 1 && lock[r][c] != index){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isIn(int r, int c){
        return 0 <= r && r < N && 0 <= c && c < N;
    }
    
    public int[][] rotate(int[][] key) {
        int[][] rotated = new int[M][M];
        for(int r = 0; r < M; r++){
            for(int c = 0; c < M; c++){
                rotated[c][M-1-r] = key[r][c];    
            }
        }
        return rotated;
    }
}