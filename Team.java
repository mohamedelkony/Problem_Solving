import java.util.*;
import static java.lang.System.out;
 
public class Team {
    private static Scanner in;
    private final static boolean muiltTestCases = false;
    private static int[][][] mem = new int[1001][2][2];

    static void solve() {
        int zeros = in.nextInt();
        int ones = in.nextInt();
        /** 
        if (zeros > ones + 1 || ones > zeros * 2 + 2) {
            out.println(-1);
            return;
        }
        */
        var s=new StringBuilder();
        int aones = 2, azeros = 1;
        while (ones >0 ||zeros>0) {
            if(ones==zeros)
            {
                if(aones>0)
                {
                    for(;ones>0;ones--)
                        s.append("10");
                    break;    
                }
                if(azeros>0){
                    for(;ones>0;ones--)
                        s.append("01");
                    break;              
                }
            }

            if (ones > zeros) {
                if (aones > 0&&ones>0) {
                    s.append(1);
                    ones--;
                    aones--;
                    azeros = 1;
                } else if (azeros > 0&&zeros>0) {
                    s.append(0);
                    zeros--;
                    azeros--;
                    aones = 2;
                }
                else
                {
                    out.println(-1);return;
                }
            }

            if (zeros > ones) {
                 if (azeros > 0&&zeros>0) {
                    s.append(0);
                    zeros--;
                    azeros--;
                    aones = 2;
                }
                else if (aones > 0&&ones>0) {
                    s.append(1);
                    ones--;
                    aones--;
                    azeros = 1;
                }
                else
                {
                    out.println(-1);return;
                }
            }
        }
        out.println(s);
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
