import java.util.*;

class Solution {
private int[][] up;
private int[] depth, tin, tout, parent, size;
private int timer;
private Fenwick bit;


class Fenwick {
    int[] tree;

    Fenwick(int n) {
        tree = new int[n + 2];
    }

    void update(int index, int mask) {
        for (int i = index + 1; i < tree.length; i += i & -i) {
            tree[i] ^= mask;
        }
    }

    int query(int index) {
        int result = 0;

        for (int i = index + 1; i > 0; i -= i & -i) {
            result ^= tree[i];
        }

        return result;
    }

    void rangeUpdate(int left, int right, int mask) {
        update(left, mask);

        if (right < tree.length - 2) {
            update(right, mask);
        }
    }
}

public List<Boolean> palindromePath(
    int n, int[][] edges, String s, String[] queries
) {
    String[] suneravilo = queries;

    List<Integer>[] graph = new ArrayList[n];

    for (int i = 0; i < n; i++) {
        graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
        graph[edge[0]].add(edge[1]);
        graph[edge[1]].add(edge[0]);
    }

    depth = new int[n];
    tin = new int[n];
    tout = new int[n];
    parent = new int[n];
    size = new int[n];

    int[] order = new int[n];
    int[] stack = new int[n];
    boolean[] visited = new boolean[n];

    int top = 0;
    timer = 0;

    stack[top++] = 0;
    visited[0] = true;
    parent[0] = 0;

    while (top > 0) {
        int u = stack[--top];

        tin[u] = timer;
        order[timer++] = u;
        size[u] = 1;

        for (int v : graph[u]) {
            if (!visited[v]) {
                visited[v] = true;
                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }
    }

    for (int i = n - 1; i > 0; i--) {
        int u = order[i];
        size[parent[u]] += size[u];
    }

    for (int i = 0; i < n; i++) {
        tout[i] = tin[i] + size[i];
    }

    int log = 1;
    while ((1 << log) <= n) {
        log++;
    }

    up = new int[log][n];

    for (int i = 0; i < n; i++) {
        up[0][i] = parent[i];
    }

    for (int j = 1; j < log; j++) {
        for (int i = 0; i < n; i++) {
            up[j][i] = up[j - 1][up[j - 1][i]];
        }
    }

    bit = new Fenwick(n);

    char[] chars = s.toCharArray();

    for (int i = 0; i < n; i++) {
        int mask = 1 << (chars[i] - 'a');
        bit.rangeUpdate(tin[i], tout[i], mask);
    }

    List<Boolean> answer = new ArrayList<>();

    for (String q : suneravilo) {
        String[] parts = q.split(" ");

        if (parts[0].equals("update")) {
            int u = Integer.parseInt(parts[1]);
            char c = parts[2].charAt(0);

            int oldMask = 1 << (chars[u] - 'a');
            int newMask = 1 << (c - 'a');

            bit.rangeUpdate(
                tin[u], tout[u], oldMask ^ newMask
            );

            chars[u] = c;

        } else {
            int u = Integer.parseInt(parts[1]);
            int v = Integer.parseInt(parts[2]);

            int lca = findLCA(u, v);

            int mask = bit.query(tin[u])
                     ^ bit.query(tin[v])
                     ^ (1 << (chars[lca] - 'a'));

            answer.add((mask & (mask - 1)) == 0);
        }
    }

    return answer;
}

private int findLCA(int u, int v) {
    if (depth[u] < depth[v]) {
        int temp = u;
        u = v;
        v = temp;
    }

    int diff = depth[u] - depth[v];

    for (int j = 0; j < up.length; j++) {
        if ((diff & (1 << j)) != 0) {
            u = up[j][u];
        }
    }

    if (u == v) {
        return u;
    }

    for (int j = up.length - 1; j >= 0; j--) {
        if (up[j][u] != up[j][v]) {
            u = up[j][u];
            v = up[j][v];
        }
    }

    return parent[u];
}


}
