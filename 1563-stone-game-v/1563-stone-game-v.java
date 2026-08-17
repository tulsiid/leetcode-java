class Solution {

    int[] prefix;
    int[][] dp;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(stoneValue, 0, n - 1);
    }

    int solve(int[] stones, int i, int j) {

        // Only one stone
        if (i >= j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int ans = 0;

        int leftSum = 0;

        int rightSum = prefix[j + 1] - prefix[i];

        for (int k = i; k < j; k++) {

            leftSum += stones[k];

            rightSum -= stones[k];

            if (leftSum < rightSum) {

                ans = Math.max(
                    ans,
                    leftSum + solve(stones, i, k)
                );

            } 
            else if (leftSum > rightSum) {

                ans = Math.max(
                    ans,
                    rightSum + solve(stones, k + 1, j)
                );

            } 
            else {

                ans = Math.max(
                    ans,
                    Math.max(
                        leftSum + solve(stones, i, k),
                        rightSum + solve(stones, k + 1, j)
                    )
                );
            }
        }

        return dp[i][j] = ans;
    }
}