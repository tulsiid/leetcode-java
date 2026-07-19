class Solution {
    public String smallestSubsequence(String s) {
        int[] last=new int[26];
        boolean[] visited=new boolean[26];
        for(int i=0;i<s.length();i++)
        {
            last[s.charAt(i) - 'a']=i;
        }
        StringBuilder st=new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(visited[ch - 'a'])
            {
                continue;
            }
            while(!st.isEmpty() && st.charAt(st.length()-1)>ch && last[st.charAt(st.length()-1) -'a']>i)
            {
                visited[st.charAt(st.length()-1) - 'a']=false;
                st.deleteCharAt(st.length()-1);
            }
            st.append(ch);
            visited[ch - 'a']=true;
        }
        return st.toString();
        
        
    }
}