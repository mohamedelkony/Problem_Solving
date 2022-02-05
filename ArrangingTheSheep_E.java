import java.util.*;
import java.io.*;

/*
https://codeforces.com/problemset/submission/1520/145201598
*/
public class ArrangingTheSheep_E {
    public static void main(String[] str) {
        boolean muiltTestCases = true;
        var cin = new FastScanner();
        PrintWriter cout = new PrintWriter(new BufferedOutputStream(System.out));
        int n = 1;
        if (muiltTestCases)
            n = cin.nextInt();
        var s = new testCaseSolution(cin, cout);
        while (n-- > 0) {
            s.solve();
            cout.println();
        }
        cout.flush();
    }
}

class testCaseSolution {
    private int[][] mem;
    private FastScanner cin;
    PrintWriter cout;

    public testCaseSolution(FastScanner cin, PrintWriter cout) {
        this.cin = cin;
        this.cout = cout;
    }

    class elem implements Comparable<elem> {
        public int value;
        public int index;

        public elem(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int compareTo(elem e) {
            return Integer.compare(this.value, e.value);
        }
    }

    void solve() {
        int n = cin.nextInt();
        String s = cin.nextLine();
        if (n == 1) {
            cout.print(0);
            return;
        }
        int[] right = new int[n];
        int[] left = new int[n];
        int current = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '*')
                current++;
            right[i] = current;
        }
        current = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*')
                current++;
            left[i] = current;
        }
        long ans = 0;
        int blank = 0;
        int left_index = 0;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) == '.' && s.charAt(i) == '.')
                blank++;
            else if (s.charAt(i - 1) == '*' && s.charAt(i) == '.') {
                blank = 1;
                left_index = i - 1;
            } else if (s.charAt(i - 1) == '.' && s.charAt(i) == '*') {
                int mn = Math.min(right[i], left[left_index]);
                ans += blank * mn;
            }
        }
        cout.print(ans);
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
