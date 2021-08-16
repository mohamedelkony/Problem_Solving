import java.util.*;
import java.io.*;


public class DiverseGarland {
    public static void main(String[] str) {
        boolean muiltTestCases = false;
        var in = new Scanner(System.in);
        int n = 1;
        if (muiltTestCases)
            n = in.nextInt();
        var s = new Solution();
            
        while (n-- > 0)
            s.solve();
        in.close();
    }
}

class Solution {
    private int[][] mem;
    String s;
    TreeMap<Character, Integer> mp = new TreeMap<Character, Integer>();
    private FastScanner in;
    PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        
    public Solution() {
        in = new FastScanner();
        mem = new int[(int) 2e5 + 5][4];
        mp.put('R', 0);
        mp.put('G', 1);
        mp.put('B', 2);
        mp.put('x', 3);
       
    }

    char foo(char c, int i) {
        int x = 0;
        if (c == 'G')
            x = 1;
        else if (c == 'B')
            x = 2;
        x += i;
        x %= 3;

        if (x == 0)
            return 'R';
        else if (x == 1)
            return 'G';
        else
            return 'B';
    }

    char third(char a, char b) {
        if (a != 'R' && b != 'R')
            return 'R';
        else if (a != 'G' && b != 'G')
            return 'G';
        else
            return 'B';
    }

    int dp(int i, char pre) {
        if (i == s.length())
            return 0;

        if (mem[i][mp.get(pre)] != -1)
            return mem[i][mp.get(pre)];
        int c1 = Integer.MAX_VALUE, c2 = Integer.MAX_VALUE;
        if (pre == s.charAt(i)) {
            c1 = dp(i + 1, foo(s.charAt(i), 1)) + 1;
            c2 = dp(i + 1, foo(s.charAt(i), 2)) + 1;
        } else {
            c1 = dp(i + 1, s.charAt(i));
            c2 = dp(i + 1, third(s.charAt(i), pre)) + 1;
        }
        int ans = Math.min(c1, c2);
        mem[i][mp.get(pre)] = ans;
        return ans;
    }

    void build(int i, char pre) {
        if (i == s.length())
            return;

        int c1 = Integer.MAX_VALUE, c2 = Integer.MAX_VALUE;
        if (pre == s.charAt(i)) {
            c1 = dp(i + 1, foo(s.charAt(i), 1)) + 1;
            c2 = dp(i + 1, foo(s.charAt(i), 2)) + 1;
            if (c1 < c2) {
                out.print(foo(s.charAt(i), 1));
                build(i + 1, foo(s.charAt(i), 1));
            } else {
                out.print(foo(s.charAt(i), 2));
                build(i + 1, foo(s.charAt(i), 2));
            }
        } else {
            c1 = dp(i + 1, s.charAt(i));
            c2 = dp(i + 1, third(s.charAt(i), pre)) + 1;
            if (c1 < c2) {
                out.print(s.charAt(i));
                build(i + 1, s.charAt(i));
            } else {
                out.print(third(s.charAt(i),pre));
                build(i + 1, third(s.charAt(i), pre));
            }
        }
    }

    void solve() {
        int n = in.nextInt();
        s = in.next();
        for (int i = 0; i < (int) (2e5 + 5); i++) {
            for (int j = 0; j < 4; j++)
                mem[i][j] = -1;
        }
        for(int i=s.length();i>=0;i--)
            {
                dp(i,'R');
                dp(i,'G');
                dp(i,'B');               
            }

        out.println(dp(0, 'x'));
        build(0,'x');

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

    public FastScanner()
    {
        br = new BufferedReader(
            new InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try {
            str = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}