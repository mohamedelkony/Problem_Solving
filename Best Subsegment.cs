//https://codeforces.com/problemset/problem/1117/A
﻿﻿using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{

    public void Solve()
    {
        int n = ReadInt();
        int mx = -1;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
        {
            arr[i] = ReadInt();
            mx = Math.Max(arr[i], mx);
        }
        int ans=0;
        int count=0;
        for (int i = 0; i < n; i++)
        {
            if(arr[i]==mx)
                count++;
            else
                count=0;
            ans=Math.Max(count,ans);
        }
        writer.WriteLine(ans);
    }


    protected static TextReader reader;
    protected static TextWriter writer;
    static void Main()
    {
        reader = new StreamReader(Console.OpenStandardInput());
        writer = new StreamWriter(Console.OpenStandardOutput());
        new Solver().Solve();
        reader.Close();
        writer.Close();
    }


    private static Queue<string> currentLineTokens = new Queue<string>();
    private static string[] ReadAndSplitLine() { return reader.ReadLine().Split(new[] { ' ', '\t', }, StringSplitOptions.RemoveEmptyEntries); }
    public static string ReadToken() { while (currentLineTokens.Count == 0) currentLineTokens = new Queue<string>(ReadAndSplitLine()); return currentLineTokens.Dequeue(); }
    public static int ReadInt() { return int.Parse(ReadToken()); }
    public static long ReadLong() { return long.Parse(ReadToken()); }
    public static double ReadDouble() { return double.Parse(ReadToken(), CultureInfo.InvariantCulture); }
    public static int[] ReadIntArray() { return ReadAndSplitLine().Select(int.Parse).ToArray(); }
    public static long[] ReadLongArray() { return ReadAndSplitLine().Select(long.Parse).ToArray(); }
    public static double[] ReadDoubleArray() { return ReadAndSplitLine().Select(s => double.Parse(s, CultureInfo.InvariantCulture)).ToArray(); }
    public static int[][] ReadIntMatrix(int numberOfRows) { int[][] matrix = new int[numberOfRows][]; for (int i = 0; i < numberOfRows; i++) matrix[i] = ReadIntArray(); return matrix; }
    public static int[][] ReadAndTransposeIntMatrix(int numberOfRows)
    {
        int[][] matrix = ReadIntMatrix(numberOfRows); int[][] ret = new int[matrix[0].Length][];
        for (int i = 0; i < ret.Length; i++) { ret[i] = new int[numberOfRows]; for (int j = 0; j < numberOfRows; j++) ret[i][j] = matrix[j][i]; }
        return ret;
    }
    public static string[] ReadLines(int quantity) { string[] lines = new string[quantity]; for (int i = 0; i < quantity; i++) lines[i] = reader.ReadLine().Trim(); return lines; }

}
