class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        HashSet<Integer> set=new HashSet<>();
        for(int n:nums)
        {
            min=Math.min(n,min);
            max=Math.max(n,max);
            set.add(n);
        }
        List<Integer> list=new ArrayList<>();
        for(int i=min+1;i<max;i++)
        {
            if(!set.contains(i))
            {
                list.add(i);
            }
        }
        return list;
        
    }
}