class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        
        if(k<=1)
        {
            return 0;
        }
        int l=0;
        int prod=1;
        int count=0;
        for(int r=0;r<nums.length;r++)
        {
            prod*=nums[r];
            while(prod>=k)
            {
                prod/=nums[l];
                l++;
            }
            count+=r-l+1;
        }
        return count;
    }
}