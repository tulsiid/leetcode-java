class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank=0;

        int tgas=0;
        int tcost=0;
        int start=0;
        for(int i=0;i<gas.length;i++)
        {
            tgas+=gas[i];
            tcost+=cost[i];
            tank+=gas[i]-cost[i];
            if(tank<0)
            {
                start=i+1;
                tank=0;


            }
        }
        if(tgas<tcost)
        {
            return -1;
        }
        return start;
    }
}