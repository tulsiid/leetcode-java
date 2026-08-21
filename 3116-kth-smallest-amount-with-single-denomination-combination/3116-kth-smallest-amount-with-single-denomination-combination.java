class Solution {

    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    public long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public long count(long x, int[] coins) {

        long result = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {

            long currentLCM = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    currentLCM = lcm(currentLCM, coins[i]);

                    if (currentLCM > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            if (bits % 2 == 1) {
                result += x / currentLCM;
            } else {
                result -= x / currentLCM;
            }
        }

        return result;
    }

    public long findKthSmallest(int[] coins, int k) {

        long left = 1;
        long right = (long) Arrays.stream(coins).min().getAsInt() * k;

        while (left < right) {

            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}