/*
https://codeforces.com/problemset/problem/1499/C
*/
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{
    static List<int> state;
    static List<List<int>> g;
    static async void Solve()
    {
        int n = ReadInt();
        var groupA = new List<int>();
        var groupA_prefix = new List<long>();
        var groupB = new List<int>();
        var groupB_prefix = new List<long>();
        var costs=new int[n];
        for (int i = 0; i < n; i++)
        {
            costs[i] = ReadInt();
            if (i % 2 == 0)
            {
                groupA.Add(costs[i]);
                if (groupA_prefix.Count == 0)
                    groupA_prefix.Add(costs[i]);
                else
                    groupA_prefix.Add(groupA_prefix.Last() + costs[i]);
            }
            else
            {
                groupB.Add(costs[i]);
                if (groupB_prefix.Count == 0)
                    groupB_prefix.Add(costs[i]);
                else
                    groupB_prefix.Add(groupB_prefix.Last() + costs[i]);
            }
        }
        var bestA = new List<long>();
        var bestB = new List<long>();
        long mn=groupA[0];
        for(int i=0;i<groupA.Count;i++)
        {
            mn=Math.Min(groupA[i],mn);
            bestA.Add(groupA_prefix[i]+mn*(n-i-1));
        }
        mn=groupB[0];
        for(int i=0;i<groupB.Count;i++)
        {
            mn=Math.Min(groupB[i],mn);
            bestB.Add(groupB_prefix[i]+mn*(n-i-1));
        }
        
        long ans = long.MaxValue;
        for (int i = 2; i <= n; i++)
        {
            int B = i / 2;
            int A = i - B;
            long c = bestA[A-1] + bestB[B-1];
            ans = Math.Min(ans, c);
        }
        Console.WriteLine(ans);
    }

    static void beforeAllTests()
    {

    }
    class Pair
    {
        public long first;
        public long second;
        public Pair(long first, long second)
        {
            this.first = first;
            this.second = second;
        }
    }
    class comper : IComparer<Pair>
    {
        public int Compare(Pair a, Pair b)
        {
            if (b.second == a.second)
                return (int)(b.first - a.first);
            return (int)(a.second - b.second);
        }
    }

    static TextReader reader;
    static TextWriter writer;
    static bool multiTestCases = true;
    static void Main()
    {
        reader = new StreamReader(Console.OpenStandardInput());
        writer = new StreamWriter(Console.OpenStandardOutput());
        int n = 1;
        beforeAllTests();
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

    private static Queue<string> currentLineTokens = new Queue<string>();
    private static string[] ReadAndSplitLine() { return reader.ReadLine().Split(new[] { ' ', '\t', }, StringSplitOptions.RemoveEmptyEntries); }
    public static string ReadToken() { while (currentLineTokens.Count == 0) currentLineTokens = new Queue<string>(ReadAndSplitLine()); return currentLineTokens.Dequeue(); }
    public static int ReadInt() { return int.Parse(ReadToken()); }
    public static long ReadLong() { return long.Parse(ReadToken()); }
    public static double ReadDouble() { return double.Parse(ReadToken(), CultureInfo.InvariantCulture); }
    public static string[] ReadLines(int quantity) { string[] lines = new string[quantity]; for (int i = 0; i < quantity; i++) lines[i] = reader.ReadLine().Trim(); return lines; }
    public static int[] ReadIntArray() { return ReadAndSplitLine().Select(int.Parse).ToArray(); }

}
 
