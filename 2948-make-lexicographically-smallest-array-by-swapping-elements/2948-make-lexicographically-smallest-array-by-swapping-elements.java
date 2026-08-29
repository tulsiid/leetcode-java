class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        int[][] pairs = new int[n][2];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int start = 0;

        while (start < n) {

            int end = start;

            while (end + 1 < n &&
                   pairs[end + 1][0] - pairs[end][0] <= limit) {
                end++;
            }

            List<Integer> values = new ArrayList<>();
            List<Integer> indexes = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                values.add(pairs[i][0]);
                indexes.add(pairs[i][1]);
            }

            Collections.sort(indexes);

            for (int i = 0; i < values.size(); i++) {
                nums[indexes.get(i)] = values.get(i);
            }

            start = end + 1;
        }

        return nums;
    }
}