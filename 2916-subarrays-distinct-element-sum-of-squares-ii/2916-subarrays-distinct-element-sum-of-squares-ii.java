import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    class SegmentTree {
        long[] sum;
        long[] squareSum;
        long[] lazy;
        int n;

        SegmentTree(int n) {
            this.n = n;
            sum = new long[4 * n];
            squareSum = new long[4 * n];
            lazy = new long[4 * n];
        }

        void apply(int node, int start, int end, long val) {
            long len = end - start + 1;

            squareSum[node] = (
                squareSum[node]
                + 2 * val * sum[node]
                + val * val % MOD * len
            ) % MOD;

            sum[node] = (sum[node] + val * len) % MOD;

            lazy[node] = (lazy[node] + val) % MOD;
        }

        void push(int node, int start, int end) {
            if (lazy[node] == 0 || start == end) {
                return;
            }

            int mid = (start + end) / 2;

            apply(node * 2, start, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, end, lazy[node]);

            lazy[node] = 0;
        }

        void update(int node, int start, int end, int l, int r) {
            if (start > r || end < l) {
                return;
            }

            if (start >= l && end <= r) {
                apply(node, start, end, 1);
                return;
            }

            push(node, start, end);

            int mid = (start + end) / 2;

            update(node * 2, start, mid, l, r);
            update(node * 2 + 1, mid + 1, end, l, r);

            sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

            squareSum[node] = (
                squareSum[node * 2] + squareSum[node * 2 + 1]
            ) % MOD;
        }

        void update(int l, int r) {
            if (l <= r) {
                update(1, 0, n - 1, l, r);
            }
        }

        long getSquareSum() {
            return squareSum[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;

        HashMap<Integer, Integer> lastSeen = new HashMap<>();

        SegmentTree tree = new SegmentTree(n);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int prev = lastSeen.getOrDefault(nums[i], -1);

            tree.update(prev + 1, i);

            lastSeen.put(nums[i], i);

            ans = (ans + tree.getSquareSum()) % MOD;
        }

        return (int) ans;
    }
}