class Solution {
    public boolean hasValidPath(int[][] grid) {
        
        int m = grid.length; int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            if(r == m - 1 && c == n - 1) return true;

            int type = grid[r][c];

            if((type == 1 || type == 3 || type == 5) && c - 1 >= 0){
                int next = grid[r][c - 1];
                if(!visited[r][c - 1] && (next == 1 || next == 4 || next == 6)){
                    visited[r][c - 1] = true;
                    q.offer(new int[]{r, c - 1});
                }
            }


            if((type == 1 || type == 4 || type == 6) && c+1 < n){
                int next = grid[r][c + 1];

                if(!visited[r][c+1] && (next == 1 || next == 3 || next == 5)){
                    visited[r][c + 1] = true;
                    q.offer(new int[] {r, c + 1});
                }
            }

            if((type == 2 || type == 5 || type == 6) && r - 1 >= 0){
                    int next = grid[r - 1][c];
                    if(!visited[r - 1][c] && (next == 2 || next == 3 || next == 4)){
                        visited[r - 1][c] = true;
                        q.offer(new int[] {r - 1, c});
                    }
            }

            if((type == 2 || type == 3 ||type == 4)&& r + 1 < m){
                int next = grid[r + 1][c];
                if(!visited[r + 1][c] && (next == 2 || next == 5 || next == 6)){
                    visited[r +1][c] = true;
                    q.offer(new int[]{r + 1, c});
                }
            }
        }

        return false;
    }
}