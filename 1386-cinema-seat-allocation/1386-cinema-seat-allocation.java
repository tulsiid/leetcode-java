class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer,Set<Integer>> map=new HashMap<>();
        for(int[] seat:reservedSeats)
        {
            int row=seat[0];
            int col=seat[1];
            map.computeIfAbsent(row,k->new HashSet<>()).add(col);
        }
        int ans=(n-map.size())*2;
        for(Set<Integer> reserved:map.values())
        {
            Boolean left=true;
            Boolean middle=true;
            Boolean right=true;
            for(int i=2;i<=5;i++)
            {
                if(reserved.contains(i))
                {
                    left=false;
                }
            }
            for(int i=4;i<=7;i++)
            {
                if(reserved.contains(i))
                {
                    middle=false;
                }
            }
            for(int i=6;i<=9;i++)
            {
                if(reserved.contains(i))
                {
                    right=false;
                }
            }
            if(left && right)
            {
                ans+=2;
            }
            else if(left || middle || right)
            {
                ans+=1;
            }
        }
        return ans;
    }
}