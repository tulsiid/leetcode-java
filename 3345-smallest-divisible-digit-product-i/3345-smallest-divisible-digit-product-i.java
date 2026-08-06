class Solution {
    public int smallestNumber(int n, int t) {
        while(true)
        {
            int product=digitproduct(n);
            if(product%t==0)
            {
                return n;
            }
            n++;
        }
    }
        private int digitproduct(int n)
        {
            int pro=1;
            while(n>0)
            {
                pro*=n%10;
                n=n/10;
            }
            return pro;
        }
        
        
    
}