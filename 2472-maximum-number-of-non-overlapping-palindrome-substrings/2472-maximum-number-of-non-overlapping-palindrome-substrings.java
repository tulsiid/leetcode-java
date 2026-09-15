class Solution {
    public int maxPalindromes(String s, int k) {

        int n = s.length();

        // dp[i] = maximum number of palindromes
        // we can take from index 0 to i-1
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            // Don't use s[i-1]
            dp[i] = dp[i - 1];

            // Check palindrome of length k
            if (i >= k && isPalindrome(s, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }

            // Check palindrome of length k+1
            if (i >= k + 1 && isPalindrome(s, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }

        return dp[n];
    }

    private boolean isPalindrome(String s, int left, int right) {

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}