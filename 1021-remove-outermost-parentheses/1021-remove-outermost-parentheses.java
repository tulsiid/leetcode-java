class Solution {
    public String removeOuterParentheses(String s) {

        StringBuilder result = new StringBuilder();
        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;

                if (balance > 1) {
                    result.append(ch);
                }
            }
            else {
                balance--;

                if (balance > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }
}