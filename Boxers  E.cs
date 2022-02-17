//https://codeforces.com/contest/1203/problem/E
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public static class Solver
{

    static void Solve()
    {
        int n = ReadInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = ReadInt();
        Array.Sort(arr, (a, b) => a - b);
        int[] hash = new int[150002];
        int ans = 0;
        foreach (int i in arr)
        {
            if (i - 1 > 0 && hash[i - 1] == 0)
            {
                hash[i - 1] = 1;
                ans++;
            }
            else if (hash[i]==0)
            {
                hash[i]=1;
                ans++;
            }
            else if(hash[i+1]==0)
            {
                hash[i+1]=1;
                ans++;
            }
        }
        writer.WriteLine(ans);
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
