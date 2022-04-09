*/
https://codeforces.com/contest/701/problem/C
*/
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
public class Solver
{
    static void Solve()
    {
        int n = ReadInt();
        string? s = reader.ReadLine();
        var comprehensive_set = new SortedSet<char>();
        foreach (char c in s)
            comprehensive_set.Add(c);
        int N_unique = comprehensive_set.Count;
        var mp = new SortedDictionary<char, int>();
        int ans = int.MaxValue;
        int checkPoint = -1;
        for (int i = 0; i < s.Length; i++)
        {
            mp[s[i]] = mp.ContainsKey(s[i]) ? mp[s[i]] + 1 : 1;
            if (mp.Count == N_unique)
            {
                ans = Math.Min(i - checkPoint, ans);
                for (int j = checkPoint + 1; j < i; j++)
                {
                    checkPoint = j;
                    if (mp[s[j]] == 1)
                        mp.Remove(s[j]);
                    else mp[s[j]]--;
                    
                    if (mp.Count == N_unique)
                        ans = Math.Min(i - checkPoint, ans);
                    else
                        break;
                }
            }
        }
        Console.WriteLine(ans);
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
}

