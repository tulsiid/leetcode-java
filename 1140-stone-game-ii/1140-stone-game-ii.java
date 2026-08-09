class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // suffix[i] = total stones from i to the end
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int M) {

        // If we can take all remaining piles
        if (i + 2 * M >= piles.length) {
            return suffix[i];
        }

        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int best = 0;

        for (int X = 1; X <= 2 * M; X++) {

            int opponent = solve(piles, i + X, Math.max(M, X));

            int current = suffix[i] - opponent;

            best = Math.max(best, current);
        }

        return dp[i][M] = best;
    }
}