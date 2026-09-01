class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set=new HashSet<>();
        int sum=0;
        int l=0;
        int max=0;
        for(int r=0;r<nums.length;r++)
        {
            while(set.contains(nums[r]))
            {
                set.remove(nums[l]);
                sum-=nums[l];
                l++;
            }
            set.add(nums[r]);
            sum+=nums[r];
            max=Math.max(max,sum);
        }
        return max;
    }
}