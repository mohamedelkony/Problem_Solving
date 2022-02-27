/*
https://codeforces.com/contest/771/problem/A
*/

using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{
    static int n, m;

    static List<List<int>> g;
    static List<int> state;
    static void Solve()
    {
        n = ReadInt();
        m = ReadInt();
        g = new List<List<int>>();
        state = new List<int>();
        for (int i = 0; i < n; i++)
        {
            g.Add(new List<int>());
            state.Add(0);
        }
        for (int i = 0; i < m; i++)
        {
            int a = ReadInt();
            int b = ReadInt();
            a--; b--;
            g[a].Add(b);
            g[b].Add(a);
        }
        bool ans = true;
        for (int i = 0; i < n; i++)
        {
            if (state[i] == 0)
            {
                var info = dfs(i, -1);
                info[0]++;
                long x=info[0];
                x=x*(x-1)/2;
                if (info[1] !=x) 
                    ans = false;
            }
        }
        writer.WriteLine(ans ? "YES" : "NO");
    }
    static int[] dfs(int root, int parent)
    {
        state[root] = 1;
        int nodes = 0; int edges = 0;
        foreach (var adj in g[root])
        {
            if (adj == parent) continue;
            if (state[adj] == 1) continue;
            edges++;
            if (state[adj] == 2) continue;
            nodes++;
            var bt = dfs(adj, root);
            nodes += bt[0];
            edges += bt[1];
        }
        state[root] = 2;
        return new int[] { nodes, edges };
    }
    static TextReader reader;
    static TextWriter writer;
    static bool multiTestCases = false;
    static void Main()
    {
        reader = new StreamReader(Console.OpenStandardInput());
        writer = new StreamWriter(Console.OpenStandardOutput());
        int n = 1;
        if (multiTestCases)
            n = ReadInt();
        while (n-- > 0)
            Solve();
        reader.Close();
        writer.Close();
    }

    private static List<int> ReadLeetCodeArray()
    {
        string s = reader.ReadLine();
        List<int> arr = new List<int>();
        s = s.Substring(1);
        s = s.Remove(s.Length - 1);
        var x = s.Split(',');
        foreach (var m in x)
            arr.Add(Int32.Parse(m));
        return arr;
    }
    private static List<List<int>> ReadLeetCodeArrayOfArrays()
    {
        string s = reader.ReadLine();
        List<List<int>> arr = new List<List<int>>();
        s = s.Substring(2);
        s = s.Remove(s.Length - 2);
        var arraysString = s.Split("],[");
        foreach (var numsString in arraysString)
        {
            var nums = numsString.Split(',');
            arr.Add(new List<int>());
            foreach (var num in nums)
                arr[arr.Count - 1].Add(Int32.Parse(num));
        }
        return arr;
    }
    private static int[][] conver(List<List<int>> x)
    {
        int[][] m = new int[x.Count][];
        for (int i = 0; i < x.Count; i++)
        {
            m[i] = x[i].ToArray();
        }
        return m;
    }
    private static Queue<string> currentLineTokens = new Queue<string>();
    private static string[] ReadAndSplitLine() { return reader.ReadLine().Split(new[] { ' ', '\t', }, StringSplitOptions.RemoveEmptyEntries); }
    public static string ReadToken() { while (currentLineTokens.Count == 0) currentLineTokens = new Queue<string>(ReadAndSplitLine()); return currentLineTokens.Dequeue(); }
    public static int ReadInt() { return int.Parse(ReadToken()); }
    public static long ReadLong() { return long.Parse(ReadToken()); }
    public static double ReadDouble() { return double.Parse(ReadToken(), CultureInfo.InvariantCulture); }

    public static string[] ReadLines(int quantity) { string[] lines = new string[quantity]; for (int i = 0; i < quantity; i++) lines[i] = reader.ReadLine().Trim(); return lines; }
    public static int[] ReadIntArray() { return ReadAndSplitLine().Select(int.Parse).ToArray(); }

}
public class Solution
{
    public IList<IList<int>> Subsets(int[] nums)
    {
        int n = (int)Math.Pow(nums.Length, 2);
        var ans = new List<IList<int>>();
        for (int i = 0; i < n; i++)
        {
            ans.Add(new List<int>());
            for (int index = 0; index < nums.Length; index++)
            {
                if (((i >> index) & 1) > 0)
                {
                    ans[ans.Count - 1].Add(nums[index]);
                }
            }
        }
        return ans;
    }

}

public class dsu
{
    private List<int> item_id = new List<int>();
    private List<int> compnent_size = new List<int>();
    private int size;
    public int Size
    {
        get
        {
            return size;
        }
    }
    public dsu(int size)
    {
        for (int i = 0; i < size; i++)
        {
            item_id.Add(i);
            compnent_size.Add(1);
        }
        this.size = size;
    }
    public int find(int id)
    {
        int root = id;
        while (root != item_id[root])
        {
            root = item_id[root];
        }
        //path comperssion
        while (root != id)
        {
            int next = item_id[id];
            item_id[id] = root;
            id = next;
        }
        return root;
    }
    public void unify(int id1, int id2)
    {
        int root1 = find(id1);
        int root2 = find(id2);

        if (root1 == root2)
            return;
        //Union by size
        if (compnent_size[root1] > compnent_size[root2])
        {
            compnent_size[root1] += compnent_size[root2];
            item_id[root2] = root1;
        }
        else
        {
            compnent_size[root2] += compnent_size[root1];
            item_id[root1] = root2;
        }
        size--;
    }
}
