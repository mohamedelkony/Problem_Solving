/*
https://codeforces.com/contest/1256/problem/D
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
        long k = ReadLong();
        string s = reader.ReadLine();
        int firstZero = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.Length; i++)
        {
            if (s[i] == '1') continue;
            if (i == firstZero)
            {
                firstZero++;
                ans.Append('0');
                continue;
            }
            long step = i - firstZero;
            if (step > k)
            {
                step = k;
                for (int x = 0; x < i - firstZero - step; x++)
                    ans.Append('1');
                ans.Append('0');
                for (int x = 0; x < step; x++)
                    ans.Append('1');
                for (int x = i+1; x < s.Length; x++)
                    ans.Append(s[x]);
                firstZero = s.Length;
                break;
            }
            else
            {
                ans.Append('0');
                firstZero++;
                k -= step;
            }
        }
        for (int x = firstZero; x < s.Length; x++)
            ans.Append('1');

        writer.WriteLine(ans.ToString());
        writer.WriteLine();
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

