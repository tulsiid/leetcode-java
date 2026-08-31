class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int c=0;
        int sum=0;
        for(int n:nums)
        {
            sum+=n;
            int req=sum-goal;
            if(map.containsKey(req))
            {
                c+=map.get(req);
            }
            map.put(sum, map.getOrDefault(sum,0)+1);
        }
        return c;

        
    }
}