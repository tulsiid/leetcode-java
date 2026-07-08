class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        final long MOD = 1_000_000_007L;
        int n = s.length();

        long[] val = new long[n + 1];
        long[] cnt = new long[n + 1];
        long[] sum = new long[n + 1];
        long[] pw  = new long[n + 1];
        pw[0] = 1;

        for (int i = 1; i <= n; i++) {
            int d = s.charAt(i - 1) - '0';
            pw[i] = pw[i - 1] * 10 % MOD;
            sum[i] = sum[i - 1] + d;
            if (d != 0) {
                val[i] = (val[i - 1] * 10 + d) % MOD;
                cnt[i] = cnt[i - 1] + 1;
            } else {
                val[i] = val[i - 1];
                cnt[i] = cnt[i - 1];
            }
        }

        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0], r = queries[i][1];
            long c = cnt[r + 1] - cnt[l];
            long x = ((val[r + 1] - val[l] * pw[(int) c]) % MOD + MOD) % MOD;
            long digitSum = sum[r + 1] - sum[l];
            ans[i] = (int) ((x * digitSum) % MOD);
        }
        return ans;
    }
}