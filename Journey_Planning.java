import java.util.*;
import static java.lang.System.out;
 
public class Journey_Planning{
    private static Scanner in;
    private final static boolean muiltTestCases = false;
    private static int[][][] mem = new int[1001][2][2];

    static void solve() {
        int n=in.nextInt();
        var arr=new int[n];
        var diff=new int[n];
        for(int i=0;i<n;i++)
        {    
            arr[i]=in.nextInt();
            diff[i]=arr[i]-i;
        }
        var sets=new TreeMap<Integer,TreeSet<Integer>>();
        for(int i=0;i<n;i++)
        {
            if(sets.containsKey(diff[i]))
            {
                sets.get(diff[i]).add(arr[i]);
            }
            else{
                var x=new TreeSet<Integer>();
                x.add(arr[i]);
                sets.put(diff[i], x);
            }
        }
        long ans=0;
        for(var st:sets.entrySet())
        {
            long sum=0;
            for(var x:st.getValue())
                sum+=x;
            ans=Math.max(ans,sum);
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
