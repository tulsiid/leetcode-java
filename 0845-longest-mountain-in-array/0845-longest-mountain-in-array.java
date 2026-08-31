class Solution {
    public int longestMountain(int[] arr) {
        int longest=0;
        int n=arr.length;
        int i=1;
        while(i<n-1)
        {
            //finding the peak element
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1])
            {
                int l=i;
                int r=i;
            
                //finding left element
                while(l>0 && arr[l-1]<arr[l])
                {
                    l--;
                }
                //finding right element
                while(r<n-1 && arr[r]>arr[r+1])
                {
                    r++;
                }
                //counting the length
                int length=r-l+1;
                longest=Math.max(longest,length);
                //becoz to put r to the curr so that we don't check it again that what we have gone through it earlier
                i=r;
            }
            else 
            {
                //to move the main pointer because we don't find the peak element
                i++;
            }
        }
        return longest;
    }
}