/*
https://codeforces.com/problemset/problem/1547/E
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
    static int n;
    static void Solve()
    {
        n = ReadInt();
        int k = ReadInt();
        var id = new int[n + 1];
        var pos = new int[k];
        var temp = new int[k];
        for (int i = 0; i < n; i++)
            id[i] = -1;

        for (int i = 0; i < k; i++)
        {
            int x = ReadInt();
            x--;
            pos[i] = x;
            id[x] = i;
        }
        for (int i = 0; i < k; i++)
        {
            int x = ReadInt();
            temp[i] = x;
        }

        var right = new SortedSet<Conditioner>(new comper());
        int leftBest = int.MaxValue;
        for (int i = 0; i < k; i++)
            right.Add(new Conditioner(pos[i], pos[i] + temp[i]));

        for (int i = 0; i < n; i++)
        {
            int ans = leftBest;
            if (right.Count > 0)
                ans = Math.Min(right.First().relativeTemp - i, leftBest);
            Console.Write(ans + " ");
            if (id[i] != -1)
            {
                right.Remove(new Conditioner(pos[id[i]], pos[id[i]] + temp[id[i]]));
                leftBest = Math.Min(leftBest, temp[id[i]]);
            }
            if (leftBest != int.MaxValue)
                leftBest++;
        }
    }
    class Conditioner
    {
        public int pos;
        public int relativeTemp;
        public Conditioner(int pos, int rel)
        {
            this.pos = pos;
            this.relativeTemp = rel;
        }
    }
    class comper : IComparer<Conditioner>
    {
        public int Compare(Conditioner a, Conditioner b)
        {
            if (b.relativeTemp == a.relativeTemp)
                return b.pos - a.pos;
            return a.relativeTemp - b.relativeTemp;
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

