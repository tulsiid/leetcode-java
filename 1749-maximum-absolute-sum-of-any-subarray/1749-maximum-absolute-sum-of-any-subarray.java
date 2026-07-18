class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int currmin=0;
        int min=nums[0];
        int currmax=0;
        int max=nums[0];
        for(int n:nums)
        {
            currmax=Math.max(n,currmax+n);
            max=Math.max(currmax,max);
            currmin=Math.min(currmin+n,n);
            min=Math.min(currmin,min);
        }
        return Math.max(max,Math.abs(min));
        
    }
}