class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int max;
        int len;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            prefix = 1;
            suffix = 1;
            max = 1;
            len = 1;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String queryString,
                                  String queryCharacters,
                                  int[] queryIndices) {

        s = queryString.toCharArray();

        int n = s.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {

            int index = queryIndices[i];

            s[index] = queryCharacters.charAt(i);

            update(1, 0, n - 1, index);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(s[l]);
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int l, int r, int index) {

        if (l == r) {
            tree[node] = new Node(s[l]);
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index);
        } else {
            update(node * 2 + 1, mid + 1, r, index);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node merge(Node a, Node b) {

        Node res = new Node(a.leftChar);

        res.len = a.len + b.len;

        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // Initially take prefix from left segment
        res.prefix = a.prefix;

        // If entire left segment is same character
        // and it matches first character of right segment
        if (a.prefix == a.len &&
            a.rightChar == b.leftChar) {

            res.prefix = a.len + b.prefix;
        }

        // Initially take suffix from right segment
        res.suffix = b.suffix;

        // If entire right segment is same character
        // and it matches last character of left segment
        if (b.suffix == b.len &&
            a.rightChar == b.leftChar) {

            res.suffix = b.len + a.suffix;
        }

        // Maximum is initially the best of either side
        res.max = Math.max(a.max, b.max);

        // If boundary characters are equal,
        // a suffix from left + prefix from right can join
        if (a.rightChar == b.leftChar) {

            res.max = Math.max(
                res.max,
                a.suffix + b.prefix
            );
        }

        return res;
    }
}