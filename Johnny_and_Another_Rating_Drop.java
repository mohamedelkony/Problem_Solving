import java.util.*;
import static java.lang.System.out;
 
public class Johnny_and_Another_Rating_Drop {
    private static Scanner in;
    private final static boolean muiltTestCases = true;
    private static int[][][] mem = new int[1001][2][2];

    static void solve() {
        long n=in.nextLong();
        long ans=0;
        long d=1;
        while(d<=n)
        {
            ans+=n/d;
            d*=2;
        }
        out.println(ans);

    }

    public static void main(String[] str) {
        in = new Scanner(System.in);
        int n = 1;
        if (muiltTestCases)
            n = in.nextInt();
        while (n-- > 0)
            solve();
        in.close();
    }
    //O(log(n))
    private static long gcd(long a,long b)
    {
	if (b == 0)return a;
	return gcd(b, a%b);
    }

    /**
     * reenterant | thread safe O(log(sqrt(n))
     **/
    @SuppressWarnings("unused")
    private static TreeSet<Integer> getDivisors(int n) {
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
    @SuppressWarnings("unused")
    private static ArrayList<Integer> primesTill_N(int n) {
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
    @SuppressWarnings("unused")
    private static TreeMap<Integer, Integer> getPrimeFactors(int n) {
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
