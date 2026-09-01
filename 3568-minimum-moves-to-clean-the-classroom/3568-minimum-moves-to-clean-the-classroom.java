import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0;
        int sc = 0;

        int litterCount = 0;

        // Find S and count L
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } 
                else if (ch == 'L') {
                    litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        // Give every litter an ID
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int id = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'L') {
                    litterId[i][j] = id;
                    id++;
                }
            }
        }

        int totalMasks = 1 << litterCount;

        /*
           visited[row][col][energy][mask]

           mask tells us which litter has been collected.
        */
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][totalMasks];

        Queue<int[]> queue = new LinkedList<>();

        // row, col, currentEnergy, mask, moves
        queue.offer(new int[]{sr, sc, energy, 0, 0});

        visited[sr][sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int currentEnergy = current[2];
            int mask = current[3];
            int moves = current[4];

            // All litter collected
            if (mask == totalMasks - 1) {
                return moves;
            }

            // Cannot move without energy
            if (currentEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = currentEnergy - 1;

                int newMask = mask;

                // If we reached litter, collect it
                if (classroom[nr].charAt(nc) == 'L') {

                    int litter = litterId[nr][nc];

                    newMask = mask | (1 << litter);
                }

                // R resets energy to maximum
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                if (!visited[nr][nc][newEnergy][newMask]) {

                    visited[nr][nc][newEnergy][newMask] = true;

                    queue.offer(new int[]{
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                    });
                }
            }
        }

        return -1;
    }
}