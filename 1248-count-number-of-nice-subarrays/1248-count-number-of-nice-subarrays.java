class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        HashMap<Integer,Integer> map=new HashMap<>();
        map.put(0,1);
        int c=0;
        int sum=0;
        for(int n:nums)
        {
            if(n%2!=0)
            {
                sum++;
            }
            int req=sum-k;
        
            if(map.containsKey(req))
            {
                c+=map.get(req);
            }
        
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        return c;
    }
}