class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int l=-1;
        int r=-1;
        int max=nums[0];
        int n=nums.length;
        for(int i=1;i<n;i++)
        {
            if(nums[i]<max)
            {
                r=i;
            }
            else
            {
                max=nums[i];
            }
        }
        int min=nums[n-1];
        for(int i=n-2;i>=0;i--)
        {
            if(nums[i]>min)
            {
                l=i;
            }
            else
            {
                min=nums[i];
            }
        }
        if(l==-1)
        {
            return 0;
        }
        return r-l+1;
    }
}