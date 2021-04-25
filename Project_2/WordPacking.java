import java.util.*;

public class WordPacking {
    public static String getAuthorName() { return "You, Allen"; }
    public static String getRyersonID() { return "500833038"; }
    private static int maxBin = 0;
    private static void count(List<String> words, int r) {
		List<List<String>> temp = new ArrayList<List<String>>();
		for(int i = 0; i < 26; i++) {
			temp.add(new ArrayList<String>());
		}
		for(int i = 0; i <= words.size()-1; i++) {//everyword
				char x = words.get(i).charAt(r);
				int num = ((int)x)-97;
				temp.get(num).add(words.get(i));
				if(temp.get(num).size() >= maxBin) {
					maxBin = temp.get(num).size();
				}
		}
	}
    // Test whether two words cannot be placed into same bin.
    private static boolean incompatible(String w1, String w2) {
        for(int i = 0; i < w1.length(); i++) {
            if(w1.charAt(i) == w2.charAt(i)) { return true; }
        }
        return false;
    }
    
    public static List<List<String>> wordPack(List<String> words) {
        // The list of bins that we build up.
    	Map<Integer,List<List<String>>> RNG = new HashMap<Integer,List<List<String>>>();
    	for(int i = 0 ; i < 5; i++) {
    		count(words,i);
    	}
    	for (int i =0; i <= 10000 ;i++) {
        	
    		List<List<String>> result = new ArrayList<List<String>>();
             outer:
        // Each word is placed greedily into the first bins that it can go in.

            	 for(String word: words) {
	        	int idx = 0;
	             // Index of the bin that we are currently examining. 
	        	
	            while(true) {
	                boolean isGood = true;
	                // Create a new empty bin into this position if needed.
	                if(idx == result.size()) { result.add(new ArrayList<String>()); }
	                // Otherwise, check if the word could go into the current bin.
	                else {
	                    for(String w: result.get(idx)) {
	                        if(incompatible(word, w)) { isGood = false; break; }                    
	                    }
	                }
	                // If the word fits into the current bin, put it there.
	                if(isGood) { result.get(idx).add(word); continue outer; }
	                // Otherwise, try the next bin.
	                else { idx++; }
	                
	            }   
	            
	        }
    		if(result.size() == maxBin+1 || result.size() == maxBin) {
    			return result;
    		}
    		
    		RNG.put(result.size(),result);  
    		Collections.shuffle(words);
    	}
    	int i =0;
    	for (int j :RNG.keySet()) {
    		if (j < i || i == 0) i = j;
    	}
        return RNG.get(i);
    }
}