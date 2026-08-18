class Solution {
    public int largestInteger(int[] nums, int k) {
        int n=nums.length;
        if(k==n)
        {
            int max=0;
            for(int n1:nums)
            {
                max=Math.max(max,n1);
            }
            return max;
        }
        int[] c=new int[51];
        for(int n1:nums)
        {
            c[n1]++;
        }
        if(k==1)
        {
            int ans=-1;
            for(int n1:nums)
            {
                if(c[n1]==1)
                {
                    ans=Math.max(ans,n1);
                }
            }
            return ans;
        }
        int ans=-1;
        if(c[nums[0]]==1)
        {
            ans=Math.max(ans,nums[0]);
        }
        if(c[nums[n-1]]==1){
            ans=Math.max(ans,nums[n-1]);
        }
        return ans;
    }
}