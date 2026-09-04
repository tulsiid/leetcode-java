class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String best="";
        for(String word:dictionary)
        {
            int i=0;
            int j=0;
            while(i<s.length() && j<word.length())
            {
                if(s.charAt(i)==word.charAt(j))
                {
                    i++;
                    j++;
                }
                else
                {
                    i++;
                }
            }
            if(j==word.length())
            {
                if(word.length()>best.length())
                {
                    best=word;
                }
                else if(word.length()==best.length() && word.compareTo(best)<0)
                {
                    best=word;
                }
            }
        }
        return best;
        
    }
}