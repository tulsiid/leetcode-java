class Solution {

    static class Interval {
        int start;
        int end;
        int weight;
        int index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    List<Interval> arr;
    State[][] dp;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr.add(new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            ));
        }

        // Sort by starting point
        arr.sort((a, b) -> {
            if (a.start != b.start)
                return Integer.compare(a.start, b.start);

            return Integer.compare(a.end, b.end);
        });

        dp = new State[n][5];

        State answer = solve(0, 4);

        int[] result = new int[answer.indices.size()];

        for (int i = 0; i < answer.indices.size(); i++) {
            result[i] = answer.indices.get(i);
        }

        return result;
    }

    private State solve(int i, int remaining) {

        // No more intervals or cannot select any more
        if (i == arr.size() || remaining == 0) {
            return new State(0, new ArrayList<>());
        }

        if (dp[i][remaining] != null) {
            return dp[i][remaining];
        }

        // OPTION 1: Don't take current interval
        State skip = solve(i + 1, remaining);

        // OPTION 2: Take current interval
        Interval curr = arr.get(i);

        int next = findNext(i + 1, curr.end);

        State nextState = solve(next, remaining - 1);

        long takeScore = curr.weight + nextState.score;

        List<Integer> takeIndices =
            new ArrayList<>(nextState.indices);

        takeIndices.add(curr.index);

        Collections.sort(takeIndices);

        State take = new State(takeScore, takeIndices);

        // Choose better option
        State best;

        if (take.score > skip.score) {
            best = take;
        }
        else if (take.score < skip.score) {
            best = skip;
        }
        else {
            // Same score → lexicographically smaller indices
            if (isSmaller(take.indices, skip.indices)) {
                best = take;
            }
            else {
                best = skip;
            }
        }

        dp[i][remaining] = best;

        return best;
    }

    // Find first interval whose start > currentEnd
    private int findNext(int left, int currentEnd) {

        int right = arr.size();

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr.get(mid).start > currentEnd) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean isSmaller(List<Integer> a, List<Integer> b) {

        for (int i = 0; i < Math.min(a.size(), b.size()); i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}