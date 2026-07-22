import java.util.*;
class Solution {
    public String minWindow(String s, String t) {
        Map<Character,Integer> map=new HashMap<>();
        for(char c:t.toCharArray())
        {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int l=0;
        int min=Integer.MAX_VALUE;
        int count=t.length();
        String ans="";
        for(int r=0;r<s.length();r++)
        {
            char ch=s.charAt(r);
            if(map.containsKey(ch))
            {
                if(map.get(ch)>0) count--;
                map.put(ch,map.get(ch)-1);
            }
            while(count==0)
            {
                if(r-l+1<min)
                {
                    min=r-l+1;
                    ans=s.substring(l,r+1);
                }
                char leftchar=s.charAt(l);
                if(map.containsKey(leftchar))
                {
                    map.put(leftchar,map.get(leftchar)+1);
                    if(map.get(leftchar)>0) count++;
                }
                l++;
            }
        }
        return ans;


        
    }
}