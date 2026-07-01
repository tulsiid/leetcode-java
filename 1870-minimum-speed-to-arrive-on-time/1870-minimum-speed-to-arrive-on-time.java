class Solution {
    public int minSpeedOnTime(int[] dist, double hour) {
        int low=1;
        int high=(int) 1e7;
        int ans=-1;
        while(low<=high)
        {
            int mid=low+(high-low)/2;
            if(canspeed(dist, hour, mid))
            {
                ans=mid;
                high=mid-1;

            }
            else
            {
                low=mid+1;
            }
        }
        return ans;
        
    }
    private boolean canspeed(int[] dist, double hour, int speed)
    {
        double time=0.0;
        for(int d=0; d<dist.length; d++)
        {
            double t=(double) dist[d]/ speed;
            if(d!=dist.length-1)
            {
                time+=Math.ceil(t);
            }
            else
            {
                time+=t;
            }
        }
        return time<=hour;

    }
}