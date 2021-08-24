import java.util.*;
import java.io.*;
/*
https://codeforces.com/problemset/submission/1380/126800275
*/
public class CreateTheTeams {
    public static void main(String[] str) {
        boolean muiltTestCases = true;
        var in = new FastScanner();
        int n = 1;
        if (muiltTestCases)
            n = in.nextInt();
        var s = new Solution(in);

        while (n-- > 0)
            s.solve();
     
    }
}

class Solution {
    private int[][] mem;
    private FastScanner in;
    PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public Solution(FastScanner in) {
        this.in = in;

    }

    void solve() {
        int n = in.nextInt();
        int x = in.nextInt();
        var arr = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        Collections.sort(arr, (a, b) -> a - b);
        var q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++)
            q.add(arr.get(i));
        int ans = 0;
        int current_num = 0;
        while (!q.isEmpty()) {
            if (q.peekLast() >= x)
                ans++;
            else {
                current_num++;
                if (current_num * q.peekLast() >= x) {
                    ans++;
                    current_num = 0;
                }
            }
            q.pollLast();
        }
        out.println(ans);
        out.flush();
    }

    // O(log(n))
    long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    /**
     * reenterant | thread safe O(log(sqrt(n))
     **/
    TreeSet<Integer> getDivisors(int n) {
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
    ArrayList<Integer> primesTill_N(int n) {
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
    TreeMap<Integer, Integer> getPrimeFactors(int n) {
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