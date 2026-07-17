class Solution {
    public double findMaxAverage(int[] nums, int k) {

        int sum = 0;

        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        int maxSum = sum;

        // Slide the window
        for (int right = k; right < nums.length; right++) {

            // Remove left element and add new right element
            sum = sum - nums[right - k] + nums[right];

            // Update maximum sum
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }
}
        
    