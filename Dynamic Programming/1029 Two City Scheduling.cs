/*
leetcode 
1029. Two City Scheduling
https://leetcode.com/problems/two-city-scheduling/
*/
public class Solution
{
        public int TwoCitySchedCost(int[][] costs)
    {
        int n = costs.Length;
        int[][][] mem = new int[n][][];
        for (int i = 0; i < n; i++)
        {
            mem[i] = new int[n][];
            for (int a = 0; a < n; a++)
            {
                mem[i][a] = new int[n];
                for (int b = 0; b < n; b++)
                    mem[i][a][b] = -1;
            }
        }
        int dp(int i, int a, int b)
        {
            if (i == n)
            {
                if (a != n / 2 && b != n / 2)
                    return int.MaxValue;
                return 0;
            }
            if (a > n / 2 || b > n / 2)
                return int.MaxValue;
            if (mem[i][a][b] != -1)
                return mem[i][a][b];
            int c1 = dp(i + 1, a + 1, b);
            if (c1 != int.MaxValue)
                c1 += costs[i][0];
            int c2 = dp(i + 1, a, b + 1);
            if (c2 != int.MaxValue)
                c2 += costs[i][1];
            int ans = Math.Min(c1, c2);
            mem[i][a][b] = ans;
            return ans;
        }
        for (int i = n - 1; i >= 0; i--)
            dp(i, 0, 0);
        return dp(0, 0, 0);
    }
}
