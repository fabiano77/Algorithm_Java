package programmers;

import java.util.*;

public class lv2_카카오_양궁대회 {
	static int maxDif = 1;
    static int[] ans = {-1};
    
    public int[] solution(int n, int[] info) {
        solve(0, n, info, new int[11]);
        return ans;
    }
    public void calcul(int[] apeachInfo, int[] ryanInfo){
        int dif = 0;
        for(int i = 0; i < 11; i++){
            if(apeachInfo[i] < ryanInfo[i]){
                dif += 10-i;
            }
            else if(apeachInfo[i]>0){
                dif -= 10-i;
            }
        }
        
        
        if(maxDif < dif){
            maxDif = dif;
            ans = ryanInfo.clone();
        }
        else if(maxDif == dif && lower(ans, ryanInfo)){
            ans = ryanInfo.clone();
        }
    }
    
    public boolean lower(int[] ans, int[] ryanInfo){
        for(int i = 10; i >= 0; i--){
            if(ryanInfo[i] == ans[i]) {
                continue;
            }
            else if(ryanInfo[i] > ans[i]){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
    
    public void solve(int idx, int left, int[] apeachInfo, int[] ryanInfo) {
        if(idx == 11 || left == 0){
            ryanInfo[10] += left;
            calcul(apeachInfo, ryanInfo);
            ryanInfo[10] -= left;
            return;
        }
        
        if(apeachInfo[idx] < left){
            int num = (apeachInfo[idx]+1);
            ryanInfo[idx] += num;
            solve(idx+1, left - num, apeachInfo, ryanInfo);
            ryanInfo[idx] -= num;
        }
        
        solve(idx+1, left, apeachInfo, ryanInfo);
        
    }
}
