//package dd;
import java.util.*;
import java.io.*;
/*
https://codeforces.com/problemset/problem/1515/C
*/
public class Phoenix_and_Towers {
    public static void main(String[] str) {
        boolean muiltTestCases = true;
        var cin = new FastScanner();
        PrintWriter cout = new PrintWriter(new BufferedOutputStream(System.out));
        int n = 1;
        if (muiltTestCases)
            n = cin.nextInt();
        var s = new testCaseSolution(cin, cout);
        while (n-- > 0)
            s.solve();
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

    class block {
        int index;
        int size;
        pole owner;

        public block(int i, int s) {
            this.size = s;
            this.index = i;
        }
    }

    class pole implements Comparable<pole> {
        int index;
        long count;
        List<block> blocks;

        public pole(int index, int count) {
            this.index = index;
            this.count = count;
            blocks = new ArrayList<block>();
        }

        public void add(block c) {
            this.count += c.size;
            blocks.add(c);
            c.owner = this;
        }

        public long getCount() {
            return count;
        }

        public int compareTo(pole e) {
            if (this.index == e.index)
                return 0;
            if (this.count == e.count)
                return -1;
            return (int) (this.count - e.count);
        }
    }

    void solve() {
        int n = cin.nextInt();
        int m = cin.nextInt();
        int x = cin.nextInt();
        var arr = new ArrayList<block>();
        for (int i = 0; i < n; i++)
            arr.add(new block(i, cin.nextInt()));
        Collections.sort(arr, (a, b) -> b.size - a.size);
        var st = new TreeSet<pole>();
        for (int i = 0; i < m; i++)
            st.add(new pole(i + 1, 0));
        for (int i = 0; i < n; i++) {
            var t = st.first();
            st.remove(t);
            t.add(arr.get(i));
            st.add(t);
        }
        long crnt_x = st.last().count - st.first().count;
        if (crnt_x <= x) {
            cout.println("YES");
            var all = new ArrayList<block>();
            for (var t : st)
                all.addAll(t.blocks);
            Collections.sort(all, (a, b) -> a.index - b.index);
            for (var b : all)
                cout.print(b.owner.index + " ");
            cout.println("");
        } else {
            cout.println("NO");
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
