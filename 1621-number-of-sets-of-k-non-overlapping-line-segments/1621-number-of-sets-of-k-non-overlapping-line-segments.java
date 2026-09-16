class Solution {
    public int numberOfSets(int n, int k) {

        long MOD = 1000000007;

        // Answer = C(n + k - 1, 2 * k)
        long ans = 1;

        for (int i = 1; i <= 2 * k; i++) {
            ans = ans * (n + k - i) % MOD;

            ans = ans * modInverse(i, MOD) % MOD;
        }

        return (int) ans;
    }

    private long modInverse(long a, long mod) {
        return power(a, mod - 2, mod);
    }

    private long power(long a, long b, long mod) {

        long result = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                result = result * a % mod;
            }

            a = a * a % mod;
            b /= 2;
        }

        return result;
    }
}