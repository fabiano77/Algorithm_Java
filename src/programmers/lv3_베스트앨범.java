package programmers;

import java.util.*;


class lv3_베스트앨범 {
	
	static class Song implements Comparable<Song>{
	    int id;
	    int play;
	    public Song(int id, int play) {
	        this.id = id;
	        this.play = play;
	    }
	    public String toString() {
	        return "Song [play="+play+", id="+id+"]";
	    }
	    @Override
	    public int compareTo(Song o){
	        if(play == o.play) return id - o.id;
	        return (play - o.play) * -1;
	    }
	}

	
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        // 장르별로 가장 많이 재생된 노래 두 개
        // 노래는 고유번호로 구분
        // 속한 노래가 가장 많이 재생된 장르를 먼저 수록
        // 장르 내에서 많이 재생된 노래를 먼저 수록
        // 장르 내에서 재생 횟수가 같은 노래 중 고유번호 낮은 노래 먼저 수록
        
        // 노래의 장르 문자열 배열 genres.   length : ~10,000, 종류 100 미만
        // 재생 횟수 정수 배열 plays.       length : ~10,000
        
        int length = genres.length;
        Map<String, PriorityQueue<Song>> cnt = new HashMap<>();
        Map<String, Integer> totalCnt = new HashMap<>();
        for(int i = 0; i < length; i++) { 
            String key = genres[i];
            if(cnt.containsKey(genres[i])){
                totalCnt.put(key, totalCnt.get(key) + plays[i]);
                cnt.get(key).offer(new Song(i, plays[i]));
            }
            else{
                totalCnt.put(key, plays[i]);
                cnt.put(key, new PriorityQueue<>());
                cnt.get(key).offer(new Song(i, plays[i]));
            }
        }
        System.out.println(totalCnt);
        
        TreeMap<Integer, String> sortedGenres = new TreeMap<>(Collections.reverseOrder());
        for(String genre : totalCnt.keySet()){
            sortedGenres.put(totalCnt.get(genre), genre);
        }
        System.out.println("sorted: "+sortedGenres);
        
        
        List<Integer> answerList = new ArrayList<>();
        for(String genre : sortedGenres.values()) {
            System.out.println(cnt.get(genre));
            for(int i = 0; i < 2 && !cnt.get(genre).isEmpty(); i++){
                answerList.add(cnt.get(genre).poll().id);
            }
        }
        
        System.out.println(answerList); 
        
        // 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return하도록
        answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}