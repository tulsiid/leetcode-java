class Solution {
    public int longestSubsequence(int[] nums) {

        int n = nums.length;
        int xor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            xor ^= num;

            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Entire array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // XOR is 0 and every element is 0
        if (!hasNonZero) {
            return 0;
        }

        // XOR is 0, but at least one element is non-zero
        return n - 1;
    }
}