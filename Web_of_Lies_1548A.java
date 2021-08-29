
import java.util.*;
import java.io.*;
/*
https://codeforces.com/problemset/problem/1548/A
*/
public class Web_of_Lies_1548A {
    public static void main(String[] str) {
        boolean muiltTestCases = false;
        var cin = new FastScanner();
        PrintWriter cout = new PrintWriter(new BufferedOutputStream(System.out));
        int n = 1;
        if (muiltTestCases)
            n = cin.nextInt();
        var s = new Solution(cin, cout);
        while (n-- > 0)
            s.solve();
        cout.flush();
    }
}

class Solution {
    private int[][] mem;
    private FastScanner cin;
    PrintWriter cout;

    public Solution(FastScanner cin, PrintWriter cout) {
        this.cin = cin;
        this.cout = cout;
    }

    void solve() {
        int n = cin.nextInt();
        int m = cin.nextInt();
        var arr = new int[n];
        Arrays.fill(arr, 0);
        int vulnerables = 0;
        for (int i = 0; i < m; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            if (b < a) {
                int x = a;
                a = b;
                b = x;
            }
            arr[a]++;
            if (arr[a] == 1)
                vulnerables++;
        }
        int q = cin.nextInt();
        for (int i = 0; i < q; i++) {
            int t = cin.nextInt();
            if (t == 1) {
                int a = cin.nextInt();
                int b = cin.nextInt();
                if (b < a) {
                    int x = a;
                    a = b;
                    b = x;
                }
                arr[a]++;
                if (arr[a] == 1)
                    vulnerables++;
            } else if (t == 2) {
                int a = cin.nextInt();
                int b = cin.nextInt();
                if (b < a) {
                    int x = a;
                    a = b;
                    b = x;
                }
                arr[a]--;
                if (arr[a] == 0)
                    vulnerables--;
            } else {
                cout.println(n-vulnerables);
            }
        }

    }
}

final class Algo {
    private Algo() {
    }

    // O(log(n))
    public static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // O(log(sqrt(n))
    public static TreeSet<Integer> getDivisors(int n) {
        var st = new TreeSet<Integer>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0)
                st.add(i);
            if (n % (n / i) == 0)
                st.add(n / i);
        }
        return st;
    }

    // O(n(log(log(N)))
    public static ArrayList<Integer> primesTill_N(int n) {
        var ans = new ArrayList<Integer>();
        var prime = new ArrayList<Integer>(n + 1);
        for (int i = 0; i < n + 1; i++)
            prime.add(1);

        for (int p = 2; p * p <= n; p++) {
            if (prime.get(p) == 1) {
                for (int i = p * p; i <= n; i += p)
                    prime.set(i, 0);
            }
        }

        for (int p = 2; p <= n; p++)
            if (prime.get(p) == 1)
                ans.add(p);
        return ans;
    }

    // O(sqrt(N))
    public static TreeMap<Integer, Integer> getPrimeFactors(int n) {
        var ans = new TreeMap<Integer, Integer>();
        var primes = primesTill_N((int) (Math.sqrt(n) + 1));
        for (int p : primes) {
            while (n % p == 0) {
                ans.putIfAbsent(p, 0);
                ans.put(p, ans.get(p) + 1);
                n /= p;
            }
        }
        if (n > 2) {
            ans.putIfAbsent(n, 0);
            ans.put(n, ans.get(n) + 1);
        }
        return ans;
    }
}

class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    public FastScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}