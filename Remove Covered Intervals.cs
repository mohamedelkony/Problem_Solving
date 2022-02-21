
//https://leetcode.com/problems/remove-covered-intervals/submissions/
public class Solution {
 
    int[] coverd = new int[1];
    public int RemoveCoveredIntervals(int[][] intervals)
    {
        int n = intervals.Length;
        Array.Sort(intervals, (a, b) =>
        {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });
        coverd = new int[n];
        for (int i = 0; i < n; i++)
            coverd[i] = 0;
        int[] window = new int[2] { intervals[0][0], intervals[0][1] };
        for (int i = 0; i < n - 1; i++)
        {
            if (window[1] >= intervals[i + 1][1])
                coverd[i + 1] = 1;
            else
                window = new int[] { intervals[i + 1][0], intervals[i + 1][1] };
        }
        return n - coverd.Aggregate(0, (count, i) => count += i);
    }
}
