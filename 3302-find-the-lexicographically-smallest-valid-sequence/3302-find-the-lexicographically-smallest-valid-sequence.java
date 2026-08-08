class Solution {
    public int[] validSequence(String word1, String word2) {

        char[] a = word1.toCharArray();
        char[] b = word2.toCharArray();

        int n = a.length;
        int m = b.length;

        // dp[i] = maximum number of characters of word2
        // that can be matched using word1[i...n-1]
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && a[i] == b[j]) {
                dp[i] = dp[i + 1] + 1;
                j--;
            } else {
                dp[i] = dp[i + 1];
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // We are allowed to change at most ONE character.
        while (i < n && j < m) {

            if (a[i] == b[j]) {

                // Exact match, so take the earliest possible index.
                ans[j] = i;
                j++;

            } else {

                // Use our one allowed mismatch here only if
                // the remaining part can still be matched.
                if (dp[i + 1] >= m - 1 - j) {

                    ans[j] = i;
                    j++;

                    i++;

                    // The one mismatch has been used.
                    break;
                }
            }

            i++;
        }

        // Not enough characters.
        if (j < m && i == n) {
            return new int[0];
        }

        // Now the mismatch has already been used,
        // so the rest must match exactly.
        while (j < m && i < n) {

            if (a[i] == b[j]) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        // If we couldn't construct all m positions,
        // there is no valid answer.
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}