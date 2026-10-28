class Solution {
public int minCost(int[][] grid) {
int m = grid.length;
int n = grid[0].length;


    boolean[][] dp = new boolean[n][1024];

    dp[0][0] = true;

    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {

            boolean[] above = dp[j].clone();

            java.util.Arrays.fill(dp[j], false);

            for (int x = 0; x < 1024; x++) {

                boolean reachable = above[x];

                if (j > 0 && dp[j - 1][x]) {
                    reachable = true;
                }

                if (reachable) {
                    int newXor = x ^ grid[i][j];
                    dp[j][newXor] = true;
                }
            }
        }
    }

    for (int x = 0; x < 1024; x++) {
        if (dp[n - 1][x]) {
            return x;
        }
    }

    return 0;
}


}
