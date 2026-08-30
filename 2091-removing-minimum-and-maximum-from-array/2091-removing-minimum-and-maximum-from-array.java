class Solution {
    public int minimumDeletions(int[] nums) {
        int minidx=0;
        int maxidx=0;
        int n=nums.length;
        for(int i=1;i<n;i++)
        {
            if(nums[i]<nums[minidx])
            {
                minidx=i;
            }
            if(nums[i]>nums[maxidx])
            {
                maxidx=i;
            }
        }
        int leftidx=Math.min(minidx,maxidx);
        int rightidx=Math.max(maxidx,minidx);
        int option1=rightidx+1;
        int option2=n-leftidx;
        int option3=(leftidx+1)+(n-rightidx);
        return Math.min(option1,Math.min(option2,option3));
        
    }
}