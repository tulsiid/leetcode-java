class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length<=1)
        {
            return intervals;
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        List<int[]> l=new ArrayList<>();
        int[] newi=intervals[0];
        l.add(newi);
        for(int[] interval:intervals)
        {
           
        
            if(interval[0]<=newi[1])
            {
                newi[1]=Math.max(interval[1],newi[1]);
            }
            else
            {
                newi=interval;
                l.add(newi);

            }
        }
        return l.toArray(new int[l.size()][]);
    }
}