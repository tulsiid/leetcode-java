class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        long sum=0;
        long max=0;
        for(int i=0;i<nums.length;i++)
        {
            sum+=nums[i];
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            if(i>=k)
            {
                int outgoing=nums[i-k];
                sum-=outgoing;
                map.put(outgoing,map.get(outgoing)-1);
                if(map.get(outgoing)==0)
                {
                    map.remove(outgoing);
                }
            }
            if(i>=k-1)
            {
                if(map.size()==k)
                {
                    max=Math.max(max,sum);
                }
            }
        }
        return max;
        
    }
}