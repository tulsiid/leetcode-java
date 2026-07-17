class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int count=0;
        int sum=0;
        for(int i=0;i<k;i++)
        {
            sum+=arr[i];
        }
        if(sum>=threshold*k)
        {
            count++;
        }
        for(int r=k;r<arr.length;r++)
        {
            sum=sum-arr[r-k]+arr[r];
        
            if(sum>=threshold*k)
            {
                count++;
            }
        }
        return count;
    }
}