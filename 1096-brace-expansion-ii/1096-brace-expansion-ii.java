class Solution {

    public List<String> braceExpansionII(String expression) {

        Set<String> result = dfs(expression, 0, expression.length());

        List<String> ans = new ArrayList<>(result);

        Collections.sort(ans);

        return ans;
    }

    private Set<String> dfs(String s, int start, int end) {

        Set<String> result = new HashSet<>();

        // Current concatenation result
        Set<String> current = new HashSet<>();
        current.add("");

        int i = start;

        while (i < end) {

            char ch = s.charAt(i);

            // Case 1: normal character
            if (ch >= 'a' && ch <= 'z') {

                Set<String> next = new HashSet<>();

                for (String str : current) {
                    next.add(str + ch);
                }

                current = next;
                i++;

            }

            // Case 2: opening brace
            else if (ch == '{') {

                int count = 1;
                int j = i + 1;

                while (count > 0) {

                    if (s.charAt(j) == '{') {
                        count++;
                    } else if (s.charAt(j) == '}') {
                        count--;
                    }

                    j++;
                }

                // Solve everything inside {...}
                Set<String> inside = dfs(s, i + 1, j - 1);

                // Concatenate current × inside
                Set<String> next = new HashSet<>();

                for (String a : current) {
                    for (String b : inside) {
                        next.add(a + b);
                    }
                }

                current = next;

                i = j;
            }

            // Case 3: comma
            else if (ch == ',') {

                // Add current part to result
                result.addAll(current);

                // Start a new expression after comma
                current = new HashSet<>();
                current.add("");

                i++;
            }
        }

        result.addAll(current);

        return result;
    }
}