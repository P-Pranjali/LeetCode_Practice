class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;

        long[][] colSum = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                colSum[j][i + 1] = colSum[j][i] + grid[i][j];
            }
        }

        // dp[h][0] = max score where current col height = h, current col is NOT scored from right yet
        // We track: dp[h] = best score up to col j with col j having height h
        // Score for col j's white cells from RIGHT neighbor is added when we know h_{j+1}
        // Score for col j's white cells from LEFT neighbor is added when transitioning from j-1
        // To avoid double counting: only score white cells of col j from LEFT (col j-1 black)
        // and white cells of col j from RIGHT (col j+1 black) — but only the EXTRA rows not covered by left

        // Correct DP:
        // dp[h1] at col j
        // Transition to h2 at col j+1:
        //   if h2 > h1: white rows h1..h2-1 of col j scored (left side of col j+1 is black, but these are col j whites)
        //     gain = colSum[j][h2] - colSum[j][h1]
        //   if h1 > h2: white rows h2..h1-1 of col j+1 scored by left black col j
        //     gain = colSum[j+1][h1] - colSum[j+1][h2]
        //   These two regions never overlap (different columns), so no double count per transition.
        //   But col j white cells (rows h1..n-1) could ALSO be scored by col j+1 if h2 > h1 (done above)
        //   AND by col j-1 — but col j-1 scoring happened in previous transition.
        //   Problem: rows h1..h2-1 of col j scored here AND rows scored by previous step may overlap.

        // Root cause: col j's white cells rows h1..n-1:
        //   - Rows h1..h2-1 scored if h2>h1 (right neighbor black)
        //   - Rows were also potentially scored by left neighbor in previous transition
        //   We must pick MAX(left contribution, right contribution) not SUM.

        // Solution: separate dp into two states:
        // dp[h][0]: col j has height h, col j white cells NOT yet scored from left
        // dp[h][1]: col j has height h, col j white cells scored from left up to some amount
        // Actually simplest fix: only score col j+1's white cells (never current col's white from right)

        // At each transition j->j+1, only add: white cells of col j+1 scored by black col j
        // i.e., rows 0..min(h1,h2_of_j+1... 
        // But we don't know h_{j+2} yet for col j+1's right side.

        // CORRECT MODEL: score of col j's white cells from right = accounted when processing col j+1
        // score of col j's white cells from left = accounted when processing col j-1
        // A cell is white in col j at row r if r >= h[j]
        // It's scored if h[j-1] > r OR h[j+1] > r
        // = scored if max(h[j-1], h[j+1]) > r AND r >= h[j]
        // So contribution of col j = colSum[j][max(h[j-1],h[j+1])] - colSum[j][h[j]]  (when max > h[j])

        // This means we need BOTH neighbors known. So dp must track previous col height.
        // dp[h_prev][h_cur] is O(n^2) states, transitions O(1) => O(n^3) total — too slow.

        // INSIGHT: split contribution of col j into:
        //   part A: from left  = colSum[j][max(h[j-1], h[j])] - colSum[j][h[j]]  -- only when h[j-1]>h[j]
        //   part B: from right = colSum[j][max(h[j+1], h[j])] - colSum[j][max(h[j-1],h[j])]  -- extra from right
        // Part A counted at transition j-1->j, Part B counted at transition j->j+1
        // So at transition j->j+1 (h1=h[j], h2=h[j+1]):
        //   Add part B of col j: max(0, colSum[j][h2] - colSum[j][max(h1, h_prev)])
        //   Add part A of col j+1: max(0, colSum[j+1][h1] - colSum[j+1][h2])  when h1>h2

        // This requires tracking h_prev too. Use dp[h1] = best score where prev-prev height gives
        // the "already scored up to" level for col j.

        // SIMPLEST correct approach: dp[h_prev][h_cur], optimize with prefix max.
        // O(n^2) states, O(n) transition with sweep => O(n^2) per column => O(n^3) total... 
        // But with careful sweep still O(n^2).

        // dp[j][h] = max score for cols 0..j, col j has height h, 
        //            INCLUDING contribution of col j's white cells from LEFT only.
        // At transition to col j+1 with height h2:
        //   1. Add white cells of col j+1 scored by col j (left of j+1): 
        //      rows h2..h1-1 if h1>h2 => colSum[j+1][h1]-colSum[j+1][h2]
        //   2. Add white cells of col j scored by col j+1 (right of j):
        //      rows h1..h2-1 if h2>h1 => colSum[j][h2]-colSum[j][h1]
        //      BUT only the part NOT already counted from left (col j-1).
        //      We don't know what was counted from left for col j without h_prev.

        // We need to track: for current col j with height h1, up to what row were col j's 
        // white cells already scored? That's min(h1, h_prev) ... actually max scored = h_prev if h_prev>h1.
        // So track "already_scored_height" = max(h_{j-1}, h_j) for col j's perspective.

        // State: dp[scored][h1] where scored = how far col j whites are scored from left
        //   scored = h_prev if h_prev > h1, else h1 (meaning none scored)
        //   => scored is always >= h1, and scored = max(h_prev, h1)

        // Transition dp[s][h1] -> dp[s2][h2] where s2 = max(h1, h2):
        //   gain from col j right side: colSum[j][max(s,h2)] - colSum[j][s]  (extra rows scored)
        //     wait: col j white rows are h1..n-1. Already scored up to s (exclusive from top, so rows h1..s-1 scored).
        //     Right neighbor scores rows h1..h2-1. Combined: rows h1..max(s,h2)-1 scored.
        //     Extra from right = colSum[j][max(s,h2)] - colSum[j][s]  if h2>s, else 0.
        //   gain from col j+1 left side: colSum[j+1][h1] - colSum[j+1][h2]  if h1>h2, else 0.
        //   s2 = max(h1, h2)  (how far col j+1 whites scored from left = h1 if h1>h2)

        // States: scored can be h1..n (since scored>=h1), for each h1 in 0..n => O(n^2) states. 
        // But scored = max(h_prev, h1), so for fixed h1, scored ranges h1..n.
        // Total states O(n^2), transitions O(1) if we use prefix max tricks.

        // Let's implement dp[s][h] (scored_height, current_height), s >= h.
        // n up to 100, so n^2 = 10000 states per column, O(n^3) total = 10^6. Fine!

        long[][] dp = new long[n + 1][n + 1]; // dp[s][h]
        final long NEG_INF = Long.MIN_VALUE / 2;
        for (long[] row : dp) java.util.Arrays.fill(row, NEG_INF);
        // Col 0: no left neighbor, nothing scored yet. s = h (no left scoring).
        for (int h = 0; h <= n; h++) {
            dp[h][h] = 0; // scored up to h (meaning rows 0..h-1 are black, whites start at h, none scored yet)
        }

        for (int j = 0; j < n - 1; j++) {
            long[][] ndp = new long[n + 1][n + 1];
            for (long[] row : ndp) java.util.Arrays.fill(row, NEG_INF);

            for (int s = 0; s <= n; s++) {
                for (int h1 = 0; h1 <= s; h1++) {
                    if (dp[s][h1] == NEG_INF) continue;
                    long base = dp[s][h1];

                    for (int h2 = 0; h2 <= n; h2++) {
                        // gain from right side of col j (extra scoring beyond s)
                        long rightGain = h2 > s ? colSum[j][h2] - colSum[j][s] : 0;
                        // gain from left side of col j+1
                        long leftGain = h1 > h2 ? colSum[j + 1][h1] - colSum[j + 1][h2] : 0;
                        int s2 = Math.max(h1, h2);
                        long val = base + rightGain + leftGain;
                        if (val > ndp[s2][h2]) {
                            ndp[s2][h2] = val;
                        }
                    }
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for (int s = 0; s <= n; s++)
            for (int h = 0; h <= s; h++)
                if (dp[s][h] != NEG_INF)
                    ans = Math.max(ans, dp[s][h]);
        return ans;
    }
}