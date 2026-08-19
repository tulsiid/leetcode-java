class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> freq=new HashMap<>();
        for(int n:nums)
        {
            freq.put(n,freq.getOrDefault(n,0)+1);
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->freq.get(a)-freq.get(b));
        for(int n:freq.keySet())
        {
            pq.offer(n);
            if(pq.size()>k)
            {
                pq.poll();
            }
        }
        int[] ans=new int[k];
        for(int i=0;i<k;i++)
        {
            ans[i]=pq.poll();
        }
        return ans;
    }
}