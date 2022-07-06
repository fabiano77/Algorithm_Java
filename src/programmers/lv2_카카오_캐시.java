package programmers;

import java.util.*;

public class lv2_카카오_캐시 {
	
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        // 캐시히트 1
        // 캐시미스 5
        // cacheSize <= 30
        // cities.size() <= 100_000
        LinkedHashSet<String> cache = new LinkedHashSet<>();
        for(String city : cities) {
            city = city.toLowerCase();
        	if(cache.contains(city)) {
        		answer += 1;
                cache.remove(city);
                cache.add(city);
        	}
        	else {
        		answer += 5;
        		cache.add(city);
        		if(cache.size() > cacheSize) cache.remove(cache.iterator().next());
        	}
        }
        
        return answer;
    }

}
