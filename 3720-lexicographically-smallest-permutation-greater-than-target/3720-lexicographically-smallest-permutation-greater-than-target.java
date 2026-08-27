class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int cur = target.charAt(i) - 'a';

            // Try to keep target[i] the same
            if (count[cur] > 0) {

                count[cur]--;
                ans.append(target.charAt(i));

            } else {

                // Cannot keep target[i].
                // Find the smallest character greater than target[i].
                for (int j = cur + 1; j < 26; j++) {

                    if (count[j] > 0) {

                        ans.append((char) ('a' + j));
                        count[j]--;

                        // Add remaining characters in sorted order
                        for (int k = 0; k < 26; k++) {
                            while (count[k] > 0) {
                                ans.append((char) ('a' + k));
                                count[k]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                // We cannot make the current position greater.
                // Need to backtrack.
                break;
            }
        }

        // Backtrack to find the rightmost position
        // where we can increase the character.
        for (int i = ans.length() - 1; i >= 0; i--) {

            count[ans.charAt(i) - 'a']++;

            int cur = target.charAt(i) - 'a';

            for (int j = cur + 1; j < 26; j++) {

                if (count[j] > 0) {

                    StringBuilder result =
                        new StringBuilder(target.substring(0, i));

                    result.append((char) ('a' + j));
                    count[j]--;

                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            result.append((char) ('a' + k));
                            count[k]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return "";
    }
}