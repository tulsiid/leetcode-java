class Solution {

    public String sortVowels(String s) {

        Map<Character, Integer> freq = new HashMap<>();
        List<Character> order = new ArrayList<>();
        Set<Character> seen = new HashSet<>();

        for (char c : s.toCharArray()) {

            if ("aeiou".indexOf(c) == -1)
                continue;

            if (!seen.contains(c)) {
                seen.add(c);
                order.add(c);
            }

            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        order.sort((a, b) -> {

            if (!freq.get(a).equals(freq.get(b)))
                return freq.get(b) - freq.get(a);

            return 0;
        });

        char[] ans = s.toCharArray();

        int idx = 0;

        for (int i = 0; i < ans.length; i++) {

            if ("aeiou".indexOf(ans[i]) == -1)
                continue;

            ans[i] = order.get(idx);

            freq.put(ans[i], freq.get(ans[i]) - 1);

            if (freq.get(ans[i]) == 0)
                idx++;
        }

        return new String(ans);
    }
}