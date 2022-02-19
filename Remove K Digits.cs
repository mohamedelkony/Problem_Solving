//https://leetcode.com/problems/remove-k-digits/
public class Solution
{
  
    public string RemoveKdigits(string s, int k)
    {
        var indexer = new SortedSet<int>[10];
        for (int i = 0; i <= 9; i++)
            indexer[i] = new SortedSet<int>();
        for (int i = 0; i < s.Length; i++)
            indexer[s[i] - '0'].Add(i);
        int start = 0;
        var ans = new List<int>();
        while (start < s.Length && k > 0)
        {
            int to = best(start, s, indexer, k);
            k -= to - start;
            start = to+1;
            ans.Add(to);
        }
        StringBuilder sb = new StringBuilder();
        foreach (int i in ans)
            if (i < s.Length)
                sb.Append(s[i]);
        if (ans.Last() < s.Length - 1)
            sb.Append(s.Substring(ans.Last() + 1));
        string f = sb.ToString();
        if(f.Length==0)
            return "0";
        
        int x = 0;
        while (f[x] == '0' && x < f.Length - 1)
            x++;
        f = f.Substring(x);
        if(f.Length==0)
        return "0";
        
        f=f.Substring(0,Math.Max(0,f.Length-k));
        if(f.Length==0)
        return "0";
        
        return f;
    }
    private int best(int start, string num, SortedSet<int>[] indexer, int k)
    {
        if (k <= 0) return -1;
        if (num.Length - start == 1)
            return start + 1;
        for (int i = 0; i <= 9; i++)
        {
            while (indexer[i].Count > 0 && indexer[i].First() < start)
            {
                indexer[i].Remove(indexer[i].First());
            }
            if (indexer[i].Count == 0)
                continue;
            if (indexer[i].First() - start <= k)
            {
                int ans = indexer[i].First();
                indexer[i].Remove(indexer[i].First());
                return ans;
            }
        }
        return -1;
    }
}
