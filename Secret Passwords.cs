//https://codeforces.com/contest/1263/problem/D
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
        SortedDictionary<char, HashSet<int>> wordsOfChar = new SortedDictionary<char, HashSet<int>>();
        for (char c = 'a'; c <= 'z'; c++)
            wordsOfChar[c] = new HashSet<int>();
        for (int i = 0; i < n; i++)
        {
            string s = reader.ReadLine();
            foreach (char c in s)
            {
                wordsOfChar[c].Add(i);
            }
        }
        var dsu = new dsu(n);
        for (char c = 'a'; c <= 'z'; c++)
        {
            if (wordsOfChar[c].Count <= 1)
                continue;
            int first = wordsOfChar[c].First();
            foreach (int i in wordsOfChar[c])
                dsu.unify(i, first);
        }
        writer.WriteLine(dsu.Size);
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
public class dsu
{
    private List<int> item_id=new List<int>();
    private List<int> compnent_size=new List<int>();
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
