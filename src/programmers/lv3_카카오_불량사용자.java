package programmers;

import java.util.*;

class lv3_카카오_불량사용자 {
    static int ans = 0;
    static Set<String> visited = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        ans = 0;
        
        boolean[] isbanned = new boolean[user_id.length];
        ban(user_id, banned_id, isbanned, 0);
        
        System.out.println(ans);
        return ans;
    }
    
    public static String banString(boolean[] isbanned){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < isbanned.length; i++){
            if(isbanned[i]) sb.append(i);
        }
        return sb.toString();
    }
    
    public static void ban(String[] user_id, String[] banned_id, boolean[] isbanned, 
                    int idx){
        if(idx == banned_id.length) {
            String banStr = banString(isbanned);
            if(!visited.contains(banStr)){
                visited.add(banStr);
                ans++;    
                System.out.println(Arrays.toString(isbanned));
            }
            return;
        }
        
        for(int i = 0; i < user_id.length; i++){
            if(isbanned[i]) continue;
            if(user_id[i].length() != banned_id[idx].length()) continue;
            if(!matching(user_id[i], banned_id[idx])) continue;
            isbanned[i] = true;
            ban(user_id, banned_id, isbanned, idx+1);
            isbanned[i] = false;
        }
    }
    
    public static boolean matching(String id, String ban_id) {
        int length = id.length();
        boolean ret = true;
        for(int i = 0; i < length; i++) {
            if(ban_id.charAt(i) != '*' && id.charAt(i) != ban_id.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
}
