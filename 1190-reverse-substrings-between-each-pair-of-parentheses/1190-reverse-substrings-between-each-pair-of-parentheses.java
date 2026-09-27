class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // Save everything before '('
                stack.push(current);

                // Start a fresh string
                current = new StringBuilder();

            } else if (ch == ')') {

                // Reverse content inside parentheses
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Attach reversed part
                previous.append(current);

                current = previous;

            } else {

                current.append(ch);
            }
        }

        return current.toString();
    }
}