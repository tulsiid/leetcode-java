class Solution {
    public int[] sortedSquares(int[] nums) {
       int l=0;
       
       int n=nums.length;
       int r=n-1;
       int k=n-1;
       int[] ans=new int[n];
       while(l<=r)
       {
        if(nums[l]*nums[l]>nums[r]*nums[r])
        {
            ans[k]=nums[l]*nums[l];
            l++;
        }
        else
        {
            ans[k]=nums[r]*nums[r];
            r--;
        }
        k--;
          
       }
       return ans;
  }
}
        
