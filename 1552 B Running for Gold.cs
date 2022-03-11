/*
https://codeforces.com/problemset/problem/1552/B
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
    static async void Solve()
    {
        n = ReadInt();
        var races = new List<int[]>[5];
        var rank = new List<int>[5];
        for (int i = 0; i < 5; i++)
        {
            races[i] = new List<int[]>();
            rank[i] = new List<int>();
        }
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                int x = ReadInt();
                races[j].Add(new int[] { i, x });
                rank[j].Add(0);
            }
        }

        foreach (var race in races)
            race.Sort((a, b) => a[1] - b[1]);


        for (int raceNumber = 0; raceNumber < 5; raceNumber++)
        {
            for (int i = 0; i < races[raceNumber].Count; i++)
                rank[raceNumber][races[raceNumber][i][0]] = i;
        }
        int winner = 0;
        for (int i = 1; i < n; i++)
            winner = fight(winner, i, rank);

        for (int i = 0; i < n; i++)
        {
            if(i==winner)continue;
            if (fight(winner, i, rank) != winner)
            {
                Console.WriteLine("-1");
                return;
            }
        }
        Console.WriteLine(winner+1);
    }
    static int fight(int a, int b, List<int>[] rank)
    {
        int Count = 0;
        for (int raceNumber = 0; raceNumber < 5; raceNumber++)
        {
            if (rank[raceNumber][a] < rank[raceNumber][b])
                Count++;
            else
                Count--;
        }
        if (Count > 0)
            return a;
        else
            return b;
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
