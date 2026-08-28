class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int[] count = new int[26];

        // Count characters of s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // Find middle character
        char middle = 0;

        for (int i = 0; i < 26; i++) {

            if (count[i] % 2 == 1) {

                // More than one odd frequency -> impossible
                if (middle != 0) {
                    return "";
                }

                middle = (char) ('a' + i);

                // Remove the middle character
                count[i]--;
            }
        }

        int n = s.length();

        /*
         * Assume that the left half of our answer
         * is exactly the same as target's left half.
         */
        for (int i = 0; i < n / 2; i++) {

            int c = target.charAt(i) - 'a';

            count[c] -= 2;
        }

        /*
         * Check if target's left half can actually
         * be used.
         */
        boolean possible = true;

        for (int x : count) {
            if (x < 0) {
                possible = false;
                break;
            }
        }

        /*
         * Case 1:
         * Left half is exactly equal to target's left half.
         *
         * We only need to compare the right half.
         */
        if (possible) {

            String left = target.substring(0, n / 2);

            StringBuilder right = new StringBuilder();

            if (middle != 0) {
                right.append(middle);
            }

            right.append(new StringBuilder(left).reverse());

            if (right.toString().compareTo(target.substring(n / 2)) > 0) {
                return left + right;
            }
        }

        /*
         * Case 2:
         * We need to change something in the left half.
         *
         * Start from the RIGHTMOST position.
         */
        for (int i = n / 2 - 1; i >= 0; i--) {

            int c = target.charAt(i) - 'a';

            // Restore the pair target[i]
            count[c] += 2;

            // Check if target[0 ... i-1] can still be used
            possible = true;

            for (int x : count) {
                if (x < 0) {
                    possible = false;
                    break;
                }
            }

            if (!possible) {
                continue;
            }

            /*
             * Find the smallest character greater
             * than target[i].
             */
            for (int j = c + 1; j < 26; j++) {

                if (count[j] < 2) {
                    continue;
                }

                // Use j as the new character at position i
                count[j] -= 2;

                StringBuilder left =
                    new StringBuilder(target.substring(0, i));

                left.append((char) ('a' + j));

                /*
                 * Fill remaining left half with
                 * smallest possible characters.
                 */
                for (int k = 0; k < 26; k++) {

                    while (count[k] >= 2) {

                        left.append((char) ('a' + k));

                        count[k] -= 2;
                    }
                }

                // Construct palindrome
                StringBuilder answer = new StringBuilder(left);

                if (middle != 0) {
                    answer.append(middle);
                }

                answer.append(
                    new StringBuilder(left).reverse()
                );

                return answer.toString();
            }
        }

        return "";
    }
}