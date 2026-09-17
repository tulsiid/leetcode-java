class Solution {
    public int minSumOfLengths(int[] arr, int target) {

        int n = arr.length;
        int INF = n + 1;

        // best[i] = minimum length of a valid subarray
        // completely inside arr[0 ... i-1]
        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = INF;
        }

        int ans = INF;

        // prefix sum -> earliest index
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);

        int prefix = 0;

        for (int i = 1; i <= n; i++) {

            prefix += arr[i - 1];

            // Carry forward the best answer
            best[i] = best[i - 1];

            if (map.containsKey(prefix - target)) {

                int start = map.get(prefix - target);

                int len = i - start;

                // We have a previous non-overlapping subarray
                if (best[start] != INF) {
                    ans = Math.min(ans, len + best[start]);
                }

                // This is the best single subarray ending at i
                best[i] = Math.min(best[i], len);
            }

            map.putIfAbsent(prefix, i);
        }

        return ans == INF ? -1 : ans;
    }
}