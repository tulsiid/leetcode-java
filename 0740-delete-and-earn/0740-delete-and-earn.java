class Solution {
    public int deleteAndEarn(int[] nums) {
        int max=0;
        for(int n:nums)
        {
            max=Math.max(n,max);
        }
        int[] points=new int[max+1];
        for(int n:nums)
        {
            points[n]+=n;
        }
        int[] dp=new int[max+1];
        Arrays.fill(dp,-1);
        return helper(points,max,dp);
    }
    private int helper(int[] points,int i,int[] dp)
    {
        if(i<0)
        {
            return 0;
        }
        if(dp[i]!=-1)
        {
            return dp[i];
        }
        int nottake=helper(points,i-1,dp);
        int take=points[i]+helper(points,i-2,dp);
        return dp[i]=Math.max(take,nottake);
    }
}