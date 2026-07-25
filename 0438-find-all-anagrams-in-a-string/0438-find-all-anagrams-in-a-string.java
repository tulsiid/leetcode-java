class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res=new ArrayList<>();
        if(s.length()<p.length())
         return res;
        Map<Character,Integer> map=new HashMap<>();
        int l=0;
        for(char c:p.toCharArray())
        {
            map.put(c,map.getOrDefault(c,0)+1);
        }
        int count=p.length();
        for(int r=0;r<s.length();r++)
        {
            char ch=s.charAt(r);
            int val=map.getOrDefault(ch,0);
            if(val>0) count--;
            map.put(ch,val-1);
        
            if(r-l+1>p.length())
            {
                char leftchar=s.charAt(l);
                int leftval=map.getOrDefault(leftchar,0);
                if(leftval>=0) count++;
                map.put(leftchar,leftval+1);
                l++;
            }
            if(count==0)
            {
                res.add(l);

            }
        
         
        }
        return res;
       
    }
    
}