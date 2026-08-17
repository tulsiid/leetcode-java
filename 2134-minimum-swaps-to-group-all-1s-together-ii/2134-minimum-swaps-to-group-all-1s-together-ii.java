class Solution {
    public int minSwaps(int[] nums) {
        int k=0;
        int n=nums.length;
        for(int num:nums)
        {
          if(num==1)
          {
            k++;
          }   
        }
        if(k<=1)
        {
            return 0;
        }
        int ones=0;
        for(int i=0;i<k;i++)
        {
            if(nums[i]==1)
            {
                ones++;
            }
        }
        int max=ones;
        for(int i=k;i<n+k-1;i++)
        {
            if(nums[i%n]==1)
            {
                ones++;
            }
            if(nums[(i-k)%n]==1)
            {
                ones--;
            }
            max=Math.max(max,ones);
        }
        return k-max;
    }
}