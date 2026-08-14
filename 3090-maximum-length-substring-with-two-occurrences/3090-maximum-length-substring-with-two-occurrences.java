class Solution {
    public int maximumLengthSubstring(String s) {
        int l=0;
        int ans=0;
        HashMap<Character, Integer> map=new HashMap<>();
        for(int r=0;r<s.length();r++)
        {
            char ch=s.charAt(r);
            map.put(ch,map.getOrDefault(ch,0)+1);
        
            while(map.get(ch)>2)
            {
                char leftc=s.charAt(l);
                map.put(leftc,map.get(leftc)-1);
                l++;

            }
            ans=Math.max(ans,r-l+1);
        }
        return ans;
    }
   
}