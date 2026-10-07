class Solution {
    public String reverseVowels(String s) {
        char ch[]=s.toCharArray();
        int l=0;
        int r=s.length()-1;
        while(l<r)
        {
            while(l<r && !isvowel(ch[l]))
            {
                l++;
            }
            while(l<r && !isvowel(ch[r]))
            {
                r--;
            }
            char temp=ch[l];
            ch[l]=ch[r];
            ch[r]=temp;
            l++;
            r--;
        }
        return new String(ch);
        
    }
    private boolean isvowel(char ch)
    {
        return ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U'|| ch=='a'|| ch=='e'|| ch=='o'||ch=='u' ||ch=='i';
    }

}