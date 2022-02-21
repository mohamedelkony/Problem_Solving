//https://codeforces.com/problemset/problem/510/B

﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{
    static string[] grid;
    static void Solve()
    {
        int rows = ReadInt();
        int coloumns = ReadInt();
        grid = new string[rows];
        for (int i = 0; i < rows; i++)
            grid[i] = reader.ReadLine();
        Console.WriteLine(dfs()>0?"Yes":"No");

    }

    static int[][] visited;
    static int dfs()
    {
        visited = new int[grid.Length][];
        int rows = grid.Length; int coloumns = grid[0].Length;
        for (int r = 0; r < rows; r++)
        {
            visited[r] = new int[coloumns];
            for (int c = 0; c < coloumns; c++)
                visited[r][c] = 0;
        }
        int num=0;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < coloumns; c++)
            {
                if (visited[r][c] != 2)
                  num+=  dfs_util(c, r,-1,-1);
            }
        }
        return num;
    }
    static int dfs_util(int crnt_x, int crnt_y,int p_x,int p_y)
    {
        visited[crnt_y][crnt_x] = 1;
        int[] xd = new int[] { 0, 0, -1, 1 };
        int[] yd = new int[] { 1, -1, 0, 0 };

        int num=0;
        for (int i = 0; i < 4; i++)
        {
            int adj_x = crnt_x + xd[i]; int adj_y = crnt_y + yd[i];
            if (adj_x < 0 || adj_y < 0) continue;
            if (adj_x >= grid[0].Length || adj_y >= grid.Length) continue;
            if(adj_x==p_x &&adj_y==p_y)continue;
            if (grid[adj_y][adj_x] != grid[crnt_y][crnt_x]) continue;

            if (visited[adj_y][adj_x] == 1)
               {
                num++;continue;
               }
            if(visited[adj_y][adj_x]==2)continue;
            num+=dfs_util(adj_x,adj_y,crnt_x,crnt_y);
        }
        visited[crnt_y][crnt_x]=2;
        return num;
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
