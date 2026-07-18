class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int currmax=0;
        int currmin=0;
        int max=nums[0];
        int min=nums[0];
        int total=0;
        for(int n:nums)
        {
            currmax=Math.max(currmax+n,n);
            max=Math.max(currmax,max);
            currmin=Math.min(n,currmin+n);
            min=Math.min(currmin,min);
            total+=n;
        }
        if(max<0)
        {
            return max;
        }
        return Math.max(max,total-min);
        
    }
}