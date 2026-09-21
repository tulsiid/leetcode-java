 class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at the
        // current position whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            int numMod = num % k;

            // For the new current position
            long[] newDp = new long[k];

            // Start a new subarray with only num
            newDp[numMod] = 1;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                int newRemainder =
                    (int) ((long) r * numMod % k);

                newDp[newRemainder] += dp[r];
            }

            // Add all subarrays ending at current position
            // to the final answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            // Move to next position
            dp = newDp;
        }

        return ans;
    }
}