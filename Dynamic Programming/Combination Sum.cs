//https://leetcode.com/problems/combination-sum/
public class Solution
{
    long[][] mem = new long[31][];
    int[] arr;
    public IList<IList<int>> CombinationSum(int[] candidates, int target)
    {
        arr = candidates;
        for (int i = 0; i < mem.Length; i++)
        {
            mem[i] = new long[501];
            for (int j = 0; j < 501; j++)
                mem[i][j] = -1;
        }
        long x = dp(0, target);
        build(0, target, new List<int>());
        return output;
    }
    long dp(int index, int target)
    {
        if (target == 0)
            return 1;
        if (index >= arr.Length || target < 0)
            return 0;
        if (mem[index][target] != -1)
            return mem[index][target];
        long ans = 0;
        for (int i = 0; i <= target / arr[index]; i++)
            ans += dp(index + 1, target - i * arr[index]);
        return mem[index][target] = ans;
    }
    IList<IList<int>> output = new List<IList<int>>();
    void build(int index, int target, IList<int> pre)
    {
        if (target == 0)
        {
            output.Add(pre);
            return;
        }
        if (index >= arr.Length || target < 0)
            return;

        long ans = 0;
        for (int i = 0; i <= target / arr[index]; i++)
        {
            if (dp(index + 1, target - i * arr[index]) > 0)
            {
                IList<int> cur = new List<int>(pre);
                for (int j = 0; j < i; j++)
                    cur.Add(arr[index]);
                build(index + 1, target - i * arr[index], cur);
            }
        }
    }
}
