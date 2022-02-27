/*
https://codeforces.com/contest/771/problem/B
*/

﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{
    static int n;
    static void Solve()
    {
        n = ReadInt();
        int k = ReadInt();
        var seq = ReadAndSplitLine();
        var ans = new List<int>();
        for (int i = 0; i < n; i++)
            ans.Add(-1);

        for (int i = 0; i < k; i++)
            ans[i] = i;
        if(seq[0]=="NO")
            ans[1]=0;
        for (int i = k; i < n; i++)
        {
            if (seq[i-k+1] == "NO")
                ans[i] = ans[i -k+1];
            else
                ans[i] =i+1;
        }

        for (int i = 0; i < n; i++)
            Console.Write(getS(ans[i]) +" " );

    }
    static string getS(int id)
    {
        id++;
        StringBuilder s=new StringBuilder();
        while(id>0)
        {
            int d=id%10;
            s.Append((char)(d+'a'));
            id/=10;
        }
        s[0]=s[0].ToString().ToUpper()[0];
        return s.ToString();
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
